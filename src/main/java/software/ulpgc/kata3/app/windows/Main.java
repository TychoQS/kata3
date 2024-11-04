package software.ulpgc.kata3.app.windows;

import software.ulpgc.kata3.architecture.control.ToggleBarchartCommand;
import software.ulpgc.kata3.architecture.io.FileTitleLoader;
import software.ulpgc.kata3.architecture.io.MoviesBarchartLoader;
import software.ulpgc.kata3.architecture.model.FromMapBarchartElementBuilder;
import software.ulpgc.kata3.architecture.model.Title;
import software.ulpgc.kata3.architecture.model.TsvTitleDeserializer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Tycho\\Documents\\title.basics.tsv");
        List<Title> titles = new FileTitleLoader(file, new TsvTitleDeserializer()).load();
        Map<Integer, Integer> titlesPerYearStats = new HashMap<>();
        Map<Title.TitleType, Integer> titlesTypeCountStats = new HashMap<>();
        for (Title title : titles) {
            titlesPerYearStats.put(title.getYear(), titlesPerYearStats.getOrDefault(title.getYear(), 0) + 1);
            titlesTypeCountStats.put(title.getTitleType(), titlesTypeCountStats.getOrDefault(title.getTitleType(), 0) + 1);
        }
        MainFrame mainFrame = new MainFrame();
        MoviesBarchartLoader loader = new MoviesBarchartLoader(new FromMapBarchartElementBuilder<>(titlesPerYearStats).build(), new FromMapBarchartElementBuilder<>(titlesTypeCountStats).build());
        mainFrame.put("toggle", new ToggleBarchartCommand(loader, mainFrame.getDisplay()));
        mainFrame.getDisplay().show(loader.load(0));
        mainFrame.setVisible(true);
    }
}
