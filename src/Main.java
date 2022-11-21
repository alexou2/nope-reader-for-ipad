import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    /**
     * @param args the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//debug+ os
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.out.println("os name = " + System.getProperty("os.name"));

        //lists mangas
        File mangaAvailable = new File("manga" + File.separator);
        System.out.print("**************************\n"+
                "the mangas available are:\n");
        for (File available : mangaAvailable.listFiles()) {
            System.out.print(available.getName()+"\n");
        }
        System.out.print("**************************\n");

        Scanner scan = new Scanner(System.in);

//HTML variables
        final String htmlHeader = ("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "<link rel=\"stylesheet\" href =\"ressources/manga.css\"/>\n"
                + "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\"\n>"
                + "<link rel=\"icon\" href=\"ressources/logo.png\" type=\"image/x-icon\" />\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "<title>Manga Reader</title>\n"
                + "</head><body>\n"
                + "<div class=\"logo\">\n<img src =\"ressources/logo.png\">\n</div>\n");
        final String htmlEnd = ("</body>\n</html>\n");

//array lists
        List<String> pageList = new ArrayList<>(Arrays.asList()); //list for manga pages

        List<String> chapterList = new ArrayList<>(Arrays.asList()); //list for chapters

        List<String> htmlList = new ArrayList<>(Arrays.asList()); //list for html files

//ask for manga name
        System.out.print("enter manga name: ");
        String mangaName = scan.nextLine();
        System.out.print("\n");

//scaning files
        File actual = new File("manga" + File.separator + mangaName);


//delete existing html files
        //finds html files
        for (File html : actual.listFiles()) {
            htmlList.add(html.getName());
        }
        //filters html files
        List<String> htmlFiles = htmlList.stream()
                .filter((String s) -> s.contains(".ht")).collect(Collectors.toList());

        int number = htmlFiles.size();

        //asks for permission before deleting files
        if (number == 0) {
            System.out.println("no files were found in " + System.getProperty("user.dir"));
        } else {
            if (number != 0) {
                System.out.println(number + " files were found in " + System.getProperty("user.dir") + ":");
                for (int a = 1; a <= number; ++a) {
                    System.out.println(htmlFiles.get(a - 1));
                }
                System.out.println("Do you want to delete them? [Y/n]");
                //if "y", delete files
                if ("y".equalsIgnoreCase(scan.nextLine())) {
                    for (int a = 1; a <= number; ++a) {
                        System.out.print("\"" + htmlFiles.get(a - 1));

                        File myObj = new File("manga" + File.separator + mangaName + File.separator + htmlFiles.get(a - 1));

                        if (myObj.delete()) {
                            System.out.println("\" was deleted");
                        }
                    }

                } else {
                    System.out.println("File(s) will not be erased");
                }

            }
        }
        //after deleting files
        String prevChapTop = null;
        String nextChapTop = null;
        String prevChapBottom= null;
        String nextChapBottom= null;



//lists chapter folders
        for (File f : actual.listFiles()) {
            String chapterName = f.getName();

            chapterList.add(chapterName);
        }
        chapterList.remove("ressources");
//chapter sorting
        //Collections.sort(chapterList, String.CASE_INSENSITIVE_ORDER);
        chapterList.sort(Comparator.nullsFirst(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder())));

        int chNumber = chapterList.size();
        System.out.println("chNumber= " + chNumber);
        for (int i = 1; i <= chNumber; ++i) {


            //variables for the link to the previous/next page
            try {
                prevChapTop = "<a button type=\"button\" class=\"btn btn-warning btn-lg\"\n" +
                        "                href=\"" + chapterList.get(i - 2) + ".html\">◄◄ Previous Chapter </a>\n";
            } catch (Exception e) {
            }

            try {
                nextChapTop = "<a button type=\"button\" class=\"btn btn-primary btn-lg\"\n" +
                        "                href=\"" + chapterList.get(i) + ".html\">Next Chapter ►►</a>\n";
            } catch (Exception e) {
            }

            try {
                nextChapBottom= "<a button type=\"button\" class=\"btn btn-primary btn-lg btn-block\"\n" +
                        "                href=\""+chapterList.get(i)+".html\">Next Chapter ►►</a>\n";
            }catch (Exception e){}


            try {
                prevChapBottom= "<a button type=\"button\" class=\"btn btn-outline-warning btn-sm\"\n" +
                        "            href=\""+chapterList.get(i-2)+".html\">◄◄ Previous Chapter </a>\n";
            }catch (Exception e){}

//writing to html
            //BufferedWriter bw = null;
            BufferedWriter
                    bw = new BufferedWriter(new FileWriter("manga" + File.separator + mangaName + File.separator + chapterList.get(i - 1) + ".html"));

//starting to write html file
            bw.write(htmlHeader);
            bw.write("<h1>" + chapterList.get(i - 1) + "</h1>\n");

            bw.write("<div class=\"top-buttons\">\n" +
                    "        <p> ");

            if (i > 1) {
                bw.write(prevChapTop);
            }
            if (i < chNumber) {
                bw.write(nextChapTop);
            }
            bw.write("</p>\n" +
                    "    </div>");


            bw.write("<div class=\"chapters\">\n");

            File manga = new File("manga" + File.separator + mangaName + File.separator + chapterList.get(i - 1));

            for (File p : manga.listFiles()) {
                String image = p.getName();

                //sorting list
                pageList.add(image);
            }
            List<String> pages = pageList.stream()
                    .filter((String s) -> s.endsWith(".jpg")).collect(Collectors.toList());

//images sorting
            //Collections.sort(pages, String.CASE_INSENSITIVE_ORDER);
            pages.sort(Comparator.nullsFirst(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder())));

            int pNumber = pages.size();
            System.out.println("pageNumber= " + pNumber);
            for (int a = 1; a <= pNumber; ++a) {

//adding images to html
                bw.write("<img src=\"" + chapterList.get(i - 1) + "/" + pages.get(a - 1) + "\">\n");
            }
//next/previous chapter

            bw.write("</div>\n" +
                    "    <div class=\"nextChap\">\n" +
                    "        <p>");

            if (i < chNumber) {
                bw.write(nextChapBottom);
            }

            if (i > 1) {
                bw.write("<p>" + prevChapBottom + "</p>");
            }


            System.out.println(pages);
            System.out.println("chapter finished");
            bw.write("</p></div>\n" + htmlEnd);
            bw.close();
            pageList.removeAll(pageList);
        }
        System.out.println("finished creating files for: " + mangaName);
    }
}

