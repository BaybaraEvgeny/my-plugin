import com.intellij.lang.ASTNode;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;

import java.util.List;

public class PSIAction extends AnAction {
    public PSIAction() {
        super("Hello");
    }

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();

        String s = "";
        if (FileEditorManager.
                getInstance(project).
                getSelectedTextEditor().
                getSelectionModel().
                getSelectedText() != null
           )
        {
            s = FileEditorManager.
                    getInstance(project).
                    getSelectedTextEditor().
                    getSelectionModel().
                    getSelectedText();
        }

        PsiFile file = PsiFileFactory.
                getInstance(project).
                createFileFromText(StdFileTypes.JAVA.getLanguage(), s);

        ASTNode root = file.getNode().copyElement();
        System.out.println(root.toString());
        print(root, "");
        System.out.println("-----------");

        //ASTNode[] children = file.getNode().getChildren(null);
        //ASTNode node = file.getNode().getFirstChildNode();
        //s = file.toString();
        /*for (ASTNode child1: root.getChildren(null)) {
            System.out.println(" " + child1.toString());
            for (ASTNode child2: child1.getChildren(null)) {
                System.out.println("  " + child2.toString());
                for (ASTNode child3: child2.getChildren(null)) {
                    System.out.println("   " + child3.toString());
                    for (ASTNode child4: child3.getChildren(null)) {
                        System.out.println("    " + child4.toString());
                    }
                }
            }
        }*/
    }

    public void print(ASTNode node, String indent){
        indent += " ";
        for (ASTNode child: node.getChildren(null)) {
            System.out.println(indent + child.toString());
            print(child, indent);
        }
    }

}