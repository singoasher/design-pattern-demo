package top.ashman.demo.pattern.delegation.delay;

/**
 * @author sunzhaojie
 * @date 2018/11/1
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("*** Get letter directly ***");
        LetterBuilder letterBuilder1 = new LetterBuilder();
        letterBuilder1.from("Atlantis");
        Letter letter = getLetter(letterBuilder1);
        letterBuilder1.to("Babylon");
        System.out.println(letter.getFrom());
        System.out.println(letter.getTo());

        System.out.println();
        System.out.println("*** Get letter via delegator ***");
        LetterBuilder letterBuilder2 = new LetterBuilder();
        letterBuilder2.from("New Atlantis");
        Letter letterDelegator = getLetterDelegator(letterBuilder2);
        letterBuilder2.to("New Babylon");

        System.out.println("--- Without building ---");
        try {
            System.out.println(letterDelegator.getFrom());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(letterDelegator.getTo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("--- Built ---");
        letterBuilder2.build();
        System.out.println(letterDelegator.getFrom());
        System.out.println(letterDelegator.getTo());
    }

    private static Letter getLetter(LetterBuilder letterBuilder) {
        return letterBuilder.build();
    }

    private static Letter getLetterDelegator(LetterBuilder letterBuilder) {
        return new LetterDelegator(letterBuilder);
    }
}
