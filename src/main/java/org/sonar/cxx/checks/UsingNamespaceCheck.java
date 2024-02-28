package org.sonar.cxx.checks;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.cxx.squidbridge.annotations.ActivatedByDefault;
import org.sonar.cxx.squidbridge.annotations.SqaleConstantRemediation;
import org.sonar.cxx.squidbridge.checks.SquidCheck;
import com.sonar.cxx.sslr.api.AstNode;
import com.sonar.cxx.sslr.api.Grammar;
import org.sonar.cxx.parser.CxxGrammarImpl;

@Rule(
    key = UsingNamespaceCheck.RULE_KEY,
    name = "Using namespace directives are not allowed",
    priority = Priority.BLOCKER,
    description = "Using namespace directives are not allowed.")
@ActivatedByDefault
@SqaleConstantRemediation("5min")
public class UsingNamespaceCheck extends SquidCheck<Grammar> {
  public static final String RULE_KEY = "UsingNamespace";
  @Override
  public void init() {
    subscribeTo(CxxGrammarImpl.usingDirective);
  }

  @Override
  public void visitNode(AstNode node) {
    getContext().createLineViolation(this, "Using namespace are not allowed.", node);
  }

}
