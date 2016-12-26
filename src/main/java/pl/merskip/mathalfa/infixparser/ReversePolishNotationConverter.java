package pl.merskip.mathalfa.infixparser;

import pl.merskip.mathalfa.core.fragment.Fragment;
import pl.merskip.mathalfa.core.fragment.FragmentException;
import pl.merskip.mathalfa.core.fragment.FragmentReader.Associative;
import pl.merskip.mathalfa.core.fragment.FragmentReader.ParenthesisType;
import pl.merskip.mathalfa.core.fragment.FragmentsSplitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class ReversePolishNotationConverter {
    
    private String plainText;
    private List<Fragment> fragments;
    
    public ReversePolishNotationConverter(FragmentsSplitter splitter) {
        splitter.addReaders(new ParenthesisFragmentReader());
        this.fragments = splitter.split();
        this.plainText = splitter.getPlainText();
    }

    public List<Fragment> convert() {
        List<Fragment> output = new ArrayList<>(fragments.size());
        Stack<Fragment> operationsStack = new Stack<>();

        for (Fragment fragment : fragments) {
            if (isOperator(fragment)){

                if (!operationsStack.empty()) {
                    Fragment o2 = operationsStack.peek();

                    while (isOperator(o2) &&
                            ((isLeftBiding(fragment) && comparePrecedence(fragment, o2) <= 0)
                                    || comparePrecedence(fragment, o2) < 0)) {

                        operationsStack.pop();
                        output.add(o2);

                        if (operationsStack.empty()) {
                            break;
                        }

                        o2 = operationsStack.peek();
                    }
                }

                operationsStack.push(fragment);
            }
            else if (isOpeningParenthesis(fragment)) {
                operationsStack.push(fragment);
            }
            else if (isClosingParenthesis(fragment)) {
                Fragment topOperation = operationsStack.peek();

                while (!isOpeningParenthesis(topOperation)) {
                    output.add(topOperation);
                    operationsStack.pop();

                    if (operationsStack.empty()) {
                        break;
                    }

                    topOperation = operationsStack.peek();
                }

                if (!operationsStack.empty()) {
                    operationsStack.pop();
                }

                if (!isOpeningParenthesis(topOperation)) {
                    throw new FragmentException("Not found opening parenthesis", plainText, fragment);
                }
            }
            else {
                output.add(fragment);
            }
        }

        while (!operationsStack.empty()) {
            Fragment fragment = operationsStack.peek();

            if (isOpeningParenthesis(fragment)) {
                throw new FragmentException("Unexpected opening parenthesis", plainText, fragment);
            }

            output.add(fragment);
            operationsStack.pop();
        }


        return output;
    }

    
    private boolean isOpeningParenthesis(Fragment fragment) {
        return fragment.getReader().getParenthesisType(fragment) == ParenthesisType.OPENING;
    }
    
    private boolean isClosingParenthesis(Fragment fragment) {
        return fragment.getReader().getParenthesisType(fragment) == ParenthesisType.CLOSING;
    }

    private boolean isLeftBiding(Fragment fragment) {
        assert isOperator(fragment);
        return fragment.getReader().getAssociative() == Associative.Left;
    }
    
    private boolean isOperator(Fragment fragment) {
        return fragment.getReader().getPrecedence() != 0;
    }

    private int comparePrecedence(Fragment fragment1, Fragment fragment2) {
        return fragment1.getReader().getPrecedence() - fragment2.getReader().getPrecedence();
    }

}
