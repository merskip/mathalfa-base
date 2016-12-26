//package pl.merskip.mathalfa;
//
//import org.junit.Assert;
//import org.junit.Test;
//import pl.merskip.mathalfa.core.Expression;
//import pl.merskip.mathalfa.elementary.RationalNumber;
//import pl.merskip.mathalfa.elementary.RationalNumberAddition;
//import pl.merskip.mathalfa.infixparser.Fragment;
//import pl.merskip.mathalfa.infixparser.FragmentsSplitter;
//
//import java.util.List;
//
//public class RPNParserTests {
//
//    @Test
//    public void rpnParser() {
//        String plainText = "1+(3+4)+1";
//        List<Fragment> fragments = new FragmentsSplitter(plainText).split();
//        Expression expression = new RPNParser(fragments, new ElementaryRegister()).parse();
//
//        Assert.assertEquals(7, expression.symbolsList().size());
//        expression.symbolsList().forEach(Assert::assertNotNull);
//
//        Class[] exceptedClasses= new Class[] {
//                RationalNumber.class,
//                RationalNumber.class,
//                RationalNumber.class,
//                RationalNumberAddition.class,
//                RationalNumberAddition.class,
//                RationalNumber.class,
//                RationalNumberAddition.class
//        };
//        for (int i = 0; i < expression.symbolsList().size(); i++) {
//            Assert.assertEquals(exceptedClasses[i], expression.symbolsList().get(i).getClass());
//        }
//    }
//}
