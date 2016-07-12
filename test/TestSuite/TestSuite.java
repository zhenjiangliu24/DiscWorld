package TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author yucunli
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    Controller.BasicFunctionTest.class,
    Controller.CardFunctionTest.class,
    Controller.LoadCSVTest.class,
    Controller.MoneyFunctionTest.class,
    Controller.PieceFunctionTest.class,
    Controller.SaveCSVTest.class,
    Controller.WinningConditionFunctionTest.class
})
public class TestSuite {
    
}
