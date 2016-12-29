package pl.merskip.mathalfa.base.test;

import pl.merskip.mathalfa.base.core.Number;
import pl.merskip.mathalfa.base.core.Operator;
import pl.merskip.mathalfa.base.core.Symbol;
import pl.merskip.mathalfa.base.core.fragment.Fragment;
import pl.merskip.mathalfa.base.core.fragment.FragmentReader;
import pl.merskip.mathalfa.base.core.fragment.FragmentsSplitter;
import pl.merskip.mathalfa.base.core.fragment.SymbolReader;
import pl.merskip.mathalfa.base.infixparser.PostfixConverter;
import pl.merskip.mathalfa.base.infixparser.PostfixParser;

import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

class TestUtils {
    
    private TestUtils() {
        
    }
    
    static void print(String plainText, List<Fragment> fragments) {
        System.out.print("\"" + plainText + "\" ->");
        fragments.forEach(fragment ->
                System.out.print(" \033[4m" + fragment.getText() + "\033[0m"));
        System.out.println();
    }
    
    static void assertEqualsFragment(int exceptedIndex, String exceptedText,
                                             Fragment fragment) {
        assertEquals(exceptedIndex, fragment.getIndex());
        assertEquals(exceptedText, fragment.getText());
    }
    
    static PostfixParser getParser() {
        return new PostfixParser(getConverter());
    }
    
    static PostfixConverter getConverter() {
        FragmentsSplitter splitter = new FragmentsSplitter();
        splitter.addWhitespaces(' ');
        splitter.addReaders(numberReader(), additionReader());
        return new PostfixConverter(splitter);
    }
    
    private static FragmentReader numberReader() {
        return new SymbolReader<Symbol>() {
            @Override
            public boolean fulfills(String buffer, char c) {
                return Character.isDigit(c);
            }
    
            @Override
            public Symbol create(Fragment fragment, Stack<Symbol> parameters) {
                int number = Integer.parseInt(fragment.getText());
                return new IntegerNumber(number);
            }
        };
    }
    
    private static FragmentReader additionReader() {
        return new SymbolReader<Symbol>() {
            @Override
            public boolean fulfills(String buffer, char c) {
                return buffer.isEmpty()
                        && c == '+';
            }
            
            @Override
            public int getPrecedence() {
                return 1;
            }
            
            @Override
            public Associative getAssociative() {
                return Associative.Left;
            }
    
            @Override
            public Symbol create(Fragment fragment, Stack<Symbol> parameters) {
                
                return new AdditionOperator(parameters.pop(), parameters.pop());
            }
        };
    }
    
    public static class IntegerNumber implements Number {
    
        int number;
    
        IntegerNumber(int number) {
            this.number = number;
        }
    
        @Override
        public String toPlainText() {
            return String.valueOf(number);
        }
    
        @Override
        public double toDouble() {
            return number;
        }
    }
    
    public static class AdditionOperator implements Operator {
    
        Symbol arg1, arg2;
    
        AdditionOperator(Symbol arg2, Symbol arg1) {
            this.arg1 = arg1;
            this.arg2 = arg2;
        }
    
        @Override
        public String toPlainText() {
            return String.format("(%s) + (%s)", arg1.toPlainText(), arg2.toPlainText());
        }
    }
    
}
