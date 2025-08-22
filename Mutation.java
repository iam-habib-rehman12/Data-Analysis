// MainApp.java
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        
        // Patient Browser Tab
        Tab patientTab = new Tab("Patients", createPatientView());
        Tab genomeTab = new Tab("Genome Browser", createGenomeBrowser());
        Tab pedigreeTab = new Tab("Pedigree", createPedigreeView());
        
        tabPane.getTabs().addAll(patientTab, genomeTab, pedigreeTab);
        
        Scene scene = new Scene(tabPane, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Genomic Data Management System");
        primaryStage.show();
    }
    
    private BorderPane createPatientView() {
        BorderPane pane = new BorderPane();
        
        // Table View
        TableView<Patient> table = new TableView<>();
        TableColumn<Patient, String> nameCol = new TableColumn<>("Name");
        TableColumn<Patient, Number> ageCol = new TableColumn<>("Age");
        TableColumn<Patient, String> sexCol = new TableColumn<>("Sex");
        
        table.getColumns().addAll(nameCol, ageCol, sexCol);
        
        try {
            table.setItems(FXCollections.observableArrayList(new PatientDAO().getAllPatients()));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Database error: " + e.getMessage()).show();
        }
        
        // Form for adding new patients
        GridPane form = new GridPane();
        TextField nameField = new TextField();
        TextField ageField = new TextField();
        ComboBox<String> sexCombo = new ComboBox<>(FXCollections.observableArrayList("Male", "Female", "Other"));
        Button addButton = new Button("Add Patient");
        
        form.addRow(0, new Label("Name:"), nameField);
        form.addRow(1, new Label("Age:"), ageField);
        form.addRow(2, new Label("Sex:"), sexCombo);
        form.add(addButton, 1, 3);
        
        pane.setCenter(table);
        pane.setBottom(form);
        
        return pane;
    }
    
    private Pane createGenomeBrowser() {
        VBox pane = new VBox(10);
        
        // Chromosome visualization
        Canvas canvas = new Canvas(1000, 100);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawChromosome(gc);
        
        // Mutation table
        TableView<Mutation> mutationTable = createMutationTable();
        
        pane.getChildren().addAll(
            new Label("Chromosome Viewer"),
            canvas,
            new Label("Detected Mutations"),
            mutationTable
        );
        
        return pane;
    }
    
    private void drawChromosome(GraphicsContext gc) {
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(50, 40, 900, 20);
        
        // Draw genes
        gc.setFill(Color.BLUE);
        gc.fillRect(150, 40, 100, 20); // Gene 1
        gc.fillRect(400, 40, 120, 20); // Gene 2
        
        // Draw mutations
        gc.setFill(Color.RED);
        gc.fillOval(180, 35, 10, 10); // Mutation in Gene 1
        gc.fillOval(450, 35, 10, 10); // Mutation in Gene 2
    }
    
    private TableView<Mutation> createMutationTable() {
        TableView<Mutation> table = new TableView<>();
        
        TableColumn<Mutation, String> geneCol = new TableColumn<>("Gene");
        TableColumn<Mutation, String> typeCol = new TableColumn<>("Type");
        TableColumn<Mutation, String> diseaseCol = new TableColumn<>("Disease Association");
        
        table.getColumns().addAll(geneCol, typeCol, diseaseCol);
        
        // Sample data
        table.setItems(FXCollections.observableArrayList(
            new Mutation("CFTR", "Deletion", "Cystic Fibrosis"),
            new Mutation("BRCA2", "SNP", "Hereditary Breast Cancer")
        ));
        
        return table;
    }
    
    private Pane createPedigreeView() {
        ScrollPane scrollPane = new ScrollPane();
        Pane pedigreePane = new Pane();
        
        // Sample pedigree drawing
        drawPedigreeMember(pedigreePane, 100, 50, "I-1", true, false); // Grandfather
        drawPedigreeMember(pedigreePane, 200, 50, "I-2", false, false); // Grandmother
        drawPedigreeMember(pedigreePane, 150, 150, "II-1", true, true); // Affected son
        
        // Connecting lines
        GraphicsContext gc = pedigreePane.getGraphicsContext2D();
        gc.strokeLine(100, 70, 150, 130); // Father to child
        gc.strokeLine(200, 70, 150, 130); // Mother to child
        gc.strokeLine(150, 50, 200, 50); // Marriage line
        
        pedigreePane.setPrefSize(500, 500);
        scrollPane.setContent(pedigreePane);
        
        return scrollPane;
    }
    
    private void drawPedigreeMember(Pane pane, double x, double y, String id, boolean isMale, boolean isAffected) {
        GraphicsContext gc = pane.getGraphicsContext2D();
        
        if (isMale) {
            gc.strokeRect(x-15, y-15, 30, 30);
        } else {
            gc.strokeOval(x-15, y-15, 30, 30);
        }
        
        if (isAffected) {
            gc.setFill(Color.RED);
            gc.fillRect(x-15, y-15, 30, 30);
        }
        
        gc.setFill(Color.BLACK);
        gc.fillText(id, x-10, y+40);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

// Mutation.java (Model)
public class Mutation {
    private String geneName;
    private String mutationType;
    private String diseaseAssociation;
    
    // Constructor, getters...
}