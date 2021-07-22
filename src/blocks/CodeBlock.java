package blocks;

import interpreter.Stmt;

public interface CodeBlock {
    Stmt toStmt();
}
