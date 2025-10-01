class Document {
    public void open() { System.out.println("Document Opened"); }
    public void save() { System.out.println("Document Saved"); }
}

interface Command {
    void execute();
}

class OpenCommand implements Command {
    private Document document;
    public OpenCommand(Document doc) { this.document = doc; }
    public void execute() { document.open(); }
}

class SaveCommand implements Command {
    private Document document;
    public SaveCommand(Document doc) { this.document = doc; }
    public void execute() { document.save(); }
}

class MenuItem {
    private Command command;
    public void setCommand(Command command) { this.command = command; }
    public void clicked() { command.execute(); }
}

public class TextEditor {
    public static void main(String[] args) {
        Document doc = new Document();
        Command open = new OpenCommand(doc);
        Command save = new SaveCommand(doc);

        MenuItem openMenuItem = new MenuItem();
        openMenuItem.setCommand(open);
        openMenuItem.clicked(); 

        MenuItem saveMenuItem = new MenuItem();
        saveMenuItem.setCommand(save);
        saveMenuItem.clicked(); 
    }
}