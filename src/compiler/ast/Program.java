package compiler.ast;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

/**
 * Class to generate the targets code files.
 *
 * Makes the necessary imports.
 */
public class Program {
    private List<AbstractCommand> commands;
    private Boolean IOFlag;

    public Program() {
        this.commands = new ArrayList<AbstractCommand>();
        this.IOFlag = false;
    }

    public void generateCTarget() {
        try {
            String filename = "output.c";
			FileWriter fw = new FileWriter(filename);
			PrintWriter pw = new PrintWriter(fw);
			StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(getCHeader());
            AbstractCommand.indentationLevel = 1;
			commands.stream().forEach(c -> {
				strBuilder.append(c.generateCCode());
			});
            strBuilder.append(getCFeet());
			pw.println(strBuilder.toString());
			pw.close();
			fw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }

    public void generateJavaTarget() {
        try {
            String filename = "Output.java";
			FileWriter fw = new FileWriter(filename);
			PrintWriter pw = new PrintWriter(fw);
			StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(getJavaHeader());
            AbstractCommand.indentationLevel = 2;
			commands.stream().forEach(c -> {
				strBuilder.append(c.generateJavaCode());
			});
            strBuilder.append(getJavaFeet());
			pw.println(strBuilder.toString());
			pw.close();
			fw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }

    private String getCHeader() {
        StringBuilder header = new StringBuilder();
        for (AbstractCommand cmd : commands) {
            if (cmd instanceof CmdWrite || cmd instanceof CmdRead) {
                this.IOFlag = true;
            }
        }
        if (this.IOFlag) {
            header.append("#include <stdio.h>\n\n");
        }
        header.append("int main(int argc, char *argv[]) {\n");
        return header.toString();
    }

    private String getCFeet() {
        StringBuilder shoes = new StringBuilder();
        shoes.append("}");
        return shoes.toString();
    }

    private String getJavaHeader() {
        StringBuilder header = new StringBuilder();
        for (AbstractCommand cmd : commands) {
            if (cmd instanceof CmdRead) {
                this.IOFlag = true;
            }
        }
        if (this.IOFlag) {
            header.append("import java.util.Scanner;\n\n");
        }
        header.append("public class Output {\n");
        header.append("\tpublic static void main(String[] args) {\n");
        if (this.IOFlag) {
            header.append("\t\tScanner scanner = new Scanner(System.in);\n");
        }
        return header.toString();
    }

    private String getJavaFeet() {
        StringBuilder shoes = new StringBuilder();
        if (this.IOFlag) {
            shoes.append("\t".repeat(AbstractCommand.indentationLevel));
            shoes.append("scanner.close();\n");
        }
        shoes.append("\t}\n");
        shoes.append("}");
        return shoes.toString();
    }

    public List<AbstractCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<AbstractCommand> commands) {
        this.commands = commands;
    }
}
