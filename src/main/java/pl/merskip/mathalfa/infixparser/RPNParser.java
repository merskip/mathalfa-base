//package pl.merskip.mathalfa.infixparser;
//
//import pl.merskip.mathalfa.core.OperationDescriptor;
//import pl.merskip.mathalfa.core.OperationDescriptor.Associative;
//import pl.merskip.mathalfa.core.Symbol;
//import pl.merskip.mathalfa.core.SymbolsRegister;
//import pl.merskip.mathalfa.elementary.ReversePolishNotationExpression;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.Stack;
//
//
//public class RPNParser {
//
//    private List<Fragment> fragments;
//    private SymbolsRegister symbolsRegister;
//
//
//    public RPNParser(List<Fragment> fragments, SymbolsRegister symbolsRegister) {
//        this.fragments = fragments;
//        this.symbolsRegister = symbolsRegister;
//    }
//
//    public ReversePolishNotationExpression parse() {
//        List<Symbol> output = new ArrayList<>(fragments.size());
//        Stack<Fragment> operationsStack = new Stack<>();
//
//        for (Fragment fragment : fragments) {
//            if (isOperator(fragment)){
//
//                if (!operationsStack.empty()) {
//                    Fragment o2 = operationsStack.peek();
//
//                    while (isOperator(o2) &&
//                            ((isLeftBiding(fragment) && comparePrecedence(fragment, o2) <= 0)
//                                    || comparePrecedence(fragment, o2) < 0)) {
//
//                        operationsStack.pop();
//                        output.add(tokenToSymbol(o2));
//
//                        if (operationsStack.empty()) {
//                            break;
//                        }
//
//                        o2 = operationsStack.peek();
//                    }
//                }
//
//                operationsStack.push(fragment);
//            }
//            else if (fragment.text.equals("(")) {
//                operationsStack.push(fragment);
//            }
//            else if (fragment.text.equals(")")) {
//                Fragment topOperation = operationsStack.peek();
//
//                while (!topOperation.text.equals("(")) {
//                    output.add(tokenToSymbol(topOperation));
//                    operationsStack.pop();
//
//                    if (operationsStack.empty()) {
//                        break;
//                    }
//
//                    topOperation = operationsStack.peek();
//                }
//
//                if (!operationsStack.empty()) {
//                    operationsStack.pop();
//                }
//
//                if (!topOperation.text.equals("(")) {
//                    throw  new ParserException("Not found opening parenthesis", fragment);
//                }
//            }
//            else {
//                output.add(tokenToSymbol(fragment));
//            }
//        }
//
//        while (!operationsStack.empty()) {
//            Fragment fragment = operationsStack.peek();
//
//            if (fragment.text.equals("(")) {
//                throw new ParserException("Unexpected parenthesis", fragment);
//            }
//
//            output.add(tokenToSymbol(fragment));
//            operationsStack.pop();
//        }
//
//
//        return new ReversePolishNotationExpression(output);
//    }
//
//    private Symbol tokenToSymbol(Fragment fragment) {
//
//        System.out.println(fragment.text);
//
//        if (symbolsRegister.symbolIsNumber(fragment.text)) {
//            return symbolsRegister.numberFromSymbol(fragment.text);
//        }
//        else if (isOperator(fragment)) {
//            return symbolsRegister.operationFromSymbol(fragment.text, operationForToken(fragment));
//        }
//        else {
//            throw new ParserException("Unknown symbol: " + fragment.text, fragment);
//        }
//    }
//
//    private boolean isOperator(Fragment fragment) {
//        if (fragment.text.length() > 1) return false;
//        String symbol = fragment.text;
//        return symbolsRegister.operationForSymbol(symbol).isPresent();
//    }
//
//    private boolean isLeftBiding(Fragment fragment) {
//        return operationForToken(fragment).getAssociative() == Associative.Left;
//    }
//
//    private int comparePrecedence(Fragment fragment1, Fragment fragment2) {
//        return precedenceForToken(fragment1) - precedenceForToken(fragment2);
//    }
//
//    private int precedenceForToken(Fragment fragment) {
//        return operationForToken(fragment).getPrecedence();
//    }
//
//    private OldOperationDescriptor operationForToken(Fragment fragment) {
//        Optional<OldOperationDescriptor> operation = symbolsRegister.operationForSymbol(fragment.text);
//        if (!operation.isPresent())
//            throw new ParserException("Unknown symbol: " + fragment.text, fragment);
//
//        return operation.get();
//    }
//}
