package View;

import algorithms.mazeGenerators.Maze;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadMazeController implements Initializable {
    public TreeView treeView_files;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> rootItem = new TreeItem<String> ("MazeFiles");
        rootItem.setExpanded(true);
        File f=new File ("./resources/MazeFile");
        File[] files = f.listFiles();
        for (int i=0;i<files.length;i++)
        {
            if (files[i].getName().equals("program.txt"))
                continue;
            rootItem.getChildren().add(new TreeItem<String>(files[i].getName()));
        }
        treeView_files.setRoot(rootItem);
    }

    public void OpenFile(ActionEvent actionEvent) {
        try {
            TreeItem<String> select= (TreeItem<String>) treeView_files.getSelectionModel().getSelectedItem();

            if (select==null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please choose a file.");
                alert.show();
            }
            else
            {
                File f=new File ("./resources/MazeFile");
                File[] files = f.listFiles();
                int i=0;
                for (i=0;i<files.length;i++)
                {
                    if (files[i].getName().equals(select.getValue()))
                        break;
                }
                ObjectInputStream intObject=new ObjectInputStream(new FileInputStream(files[i]));
                MazeData.maze=(Maze)intObject.readObject();
                ((Node)actionEvent.getSource()).getScene().getWindow().hide();
            }
        }catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("   ");
            alert.show();
        }
    }
}