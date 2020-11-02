package cli;

import mediaDB.Tag;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Console implements IConsole {

    private final BufferedReader reader;

    public Console() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Console(BufferedReader reader) {
        if (reader == null) {
            throw new NullPointerException("Input stream is null");
        }
        this.reader = reader;
    }

    public String readInput(String message) {
        do {
            try {
                return getReadInput(message);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public String input(String message) {
        do {
            try {
                System.out.println(message);
                return reader.readLine();
            } catch (IOException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }

        } while (true);
    }

    private String getReadInput(String message) {
        do {
            try {
                System.out.println(message);
                String input = reader.readLine();

                if (input.length() == 0 || input.isEmpty()) {
                    throw new IllegalArgumentException("Length is empty or 0");
                }


                return input;

            } catch (IOException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }

        } while (true);
    }

    @Override
    public String readInput(String message, List<String> allowOptions) {
        do {
            try {
                System.out.println(message);
                String input = reader.readLine().trim();

                if (allowOptions == null) {
                    return input;
                }

                for (String allowOption : allowOptions) {
                    if (allowOption.compareTo(input.trim()) == 0) {
                        return input;
                    }
                }

            } catch (IOException | NullPointerException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    @Override
    public Integer inputInteger(String message) {
        do {
            try {
                System.out.println(message);
                int input = Integer.parseInt(reader.readLine());

                return input;

            } catch (NumberFormatException | IOException e) {
                System.out.println(e.getMessage());
            }

        } while (true);
    }

    @Override
    public Long inputLong(String message) {

        do {
            try {
                System.out.println(message);
                Long input = Long.parseLong(reader.readLine());

                return input;

            } catch (NumberFormatException | IOException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    @Override
    public Duration inputDuration(String message) {

        final String first = "PT";

        do {
            try {
                System.out.println(message);

                Duration input = Duration.parse(first + reader.readLine().trim());

                return input;
            } catch (IOException | DateTimeException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    @Override
    public Collection<Tag> inputCollection(String message, String triggerElement) {
        do {
            try {
                return getInputCollection("Tags: ", triggerElement);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private Collection<Tag> getInputCollection(String message, String triggerElement) throws IllegalArgumentException {

        Collection<Tag> tags = new ArrayList<>();

        do {
            try {
                System.out.println(message + " | " + Arrays.toString(Tag.values()));

                String input = reader.readLine().trim();
                Tag tag = Tag.valueOf(input);

                //elem equals
                if (tags.contains(tag)) {
                    throw new IllegalArgumentException("Element wurde schon hinzugefügt");
                } else {
                    tags.add(tag);
                }

                System.out.println("Add more press " + triggerElement);
                String next = reader.readLine().trim();
                if (next.compareTo(triggerElement) != 0) {
                    return tags;
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } while (true);
    }

}
