package compiler.main;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class CustomErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                            int charPositionInLine, String message, RecognitionException e) {
        Token token = (Token) offendingSymbol;
        String tokenText = token.getText();
        ParserRuleContext context = ((Parser)recognizer).getContext();
        int ruleIndex = context.getRuleIndex();
        String[] ruleNames = recognizer.getRuleNames();
        String ruleName = ruleNames[ruleIndex];

        String errorMessage = "Parsing error occurred at line " + line +
                ", position " + charPositionInLine +
                ": Unexpected token '" + tokenText + "' in rule '" + ruleName + "'";

        throw new ParseCancellationException(errorMessage);
    }
}
