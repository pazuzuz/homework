package stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import stqa.pft.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {
    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target File")
    public String filePath;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        UserDataGenerator generator = new UserDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<UserData> users = generateGroups(count);
        switch (format) {
            case "csv":
                saveAsCSV(users, new File(filePath));
                break;
            case "xml":
                saveAsXML(users, new File(filePath));
                break;
            case "json":
                saveAsJSON(users, new File(filePath));
                break;
            default:
                System.out.println("Unrecognized format:  " + format);
                break;
        }
    }

    private void saveAsJSON(List<UserData> users, File filePath) throws IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String json = gson.toJson(users);
        try (Writer writer = new FileWriter(filePath)){
            writer.write(json);
        }
    }

    private void saveAsXML(List<UserData> users, File filePath) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(UserData.class);
        String xml = xStream.toXML(users);
        try (Writer writer = new FileWriter(filePath)){
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<UserData> users, File filePath) throws IOException {
        try (Writer writer = new FileWriter(filePath)){
            for (UserData user: users) {
                writer.write(String.format("%s;%s;%s;%s;\n",
                        user.getFirstName(),
                        user.getLastName(),
                        user.getFirstEmail(),
                        user.getMobilePhone()
                ));
            }
        }
    }

    private List<UserData> generateGroups(int count) {
        List<UserData> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(new UserData()
                    .withFirstName(String.format("Morbo %s", i))
                    .withLastName(String.format("Annulyator %s", i))
                    .withFirstEmail(String.format("morbo@gmail.com%s", i))
                    .withMobilePhone("476587346553746"))
                    ;
        }
        return users;
    }
}
