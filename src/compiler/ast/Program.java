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
    private Boolean cIOFlag;
    private Boolean javaIOFlag;

    public Program() {
        this.commands = new ArrayList<AbstractCommand>();
        this.cIOFlag = false;
        this.javaIOFlag = false;
    }

    public void generateCTarget(String name) {
        try {
            String filename = name + ".c";
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

    public void generateJavaTarget(String name) {
        try {
            String filename = name + ".java";
			FileWriter fw = new FileWriter(filename);
			PrintWriter pw = new PrintWriter(fw);
			StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(getJavaHeader(name));
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
                this.cIOFlag = true;
            }
        }
        header.append("#include <string.h>\n");
        if (this.cIOFlag) {
            header.append("#include <stdio.h>\n\n");
        }
        header.append("int main(void) {\n");
        return header.toString();
    }

    private String getCFeet() {
        StringBuilder shoes = new StringBuilder();
        shoes.append("}");
        return shoes.toString();
    }

    private String getJavaHeader(String className) {
        StringBuilder header = new StringBuilder();
        for (AbstractCommand cmd : commands) {
            if (cmd instanceof CmdRead) {
                this.javaIOFlag = true;
            }
        }
        if (this.javaIOFlag) {
            header.append("import java.util.Scanner;\n\n");
        }
        header.append("public class " + className + " {\n");
        header.append("\tpublic static void main(String[] args) {\n");
        AbstractCommand.indentationLevel = 2;
        if (this.javaIOFlag) {
            header.append("\t".repeat(AbstractCommand.indentationLevel));
            header.append("try (Scanner scanner = new Scanner(System.in)) {\n");
            AbstractCommand.indentationLevel = 3;
        }
        return header.toString();
    }

    private String getJavaFeet() {
        StringBuilder shoes = new StringBuilder();
        if (this.javaIOFlag) {
            shoes.append("\t".repeat(AbstractCommand.indentationLevel - 1));
            shoes.append("}\n");
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
