package h01;

import org.sourcegrade.jagr.api.rubric.*;
import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.criterion;

public class H01_RubricProvider implements RubricProvider {

    private static final Criterion H1_1 = Criterion.builder()
            .shortDescription("H1.1 | Steuerung von Pacman")
            .maxPoints(4)
            .addChildCriteria(
                    criterion("Pacman kann sich nach links, rechts, oben und unten bewegen."),
                    criterion("Pacman kann sich nicht durch Wände bewegen."),
                    criterion("Pacman sammelt Münzen auf, wenn er sich auf ein Feld mit einer Münze bewegt."))
            .build();

    private static final Criterion H1_2_1 = Criterion.builder()
            .shortDescription("H1.2.1 | Blaues Gespenst")
            .maxPoints(2)
            .addChildCriteria(
                    criterion("Das blaue Gespenst dreht sich nach rechts."),
                    criterion(
                            "Wenn eine Wand vor dem blauen Gespenst ist, dreht es sich nach links, bis es keine Wand mehr vor sich hat."),
                    critertion("Das blaue Gespenst bewegt sich ein Feld nach vorne."))
            .build();

    private static final Criterion H1_2_2 = Criterion.builder()
            .shortDescription("H1.2.2 | Pinkes Gespenst")
            .maxPoints(3)
            .addChildCriteria(
                    criterion("Das pinke Gespenst findet die korrekte Anzahl an freien Wegen."),
                    criterion("Das pinke Gespenst wählt einen zufälligen Weg aus den freien Wegen."),
                    criterion("Das pinke Gespenst dreht sich zu dem gewählten freien Weg."),
                    criterion("Das pinke Gespenst bewegt sich ein Feld nach vorne."))
            .build();

    private static final Criterion H1_2_3 = Criterion.builder()
            .shortDescription("H1.2.3 | Oranges Gespenst")
            .maxPoints(4)
            .addChildCriteria(
                    criterion("Das orange Gespenst geht einen Schritt nach vorne, wenn keine Wand vor ihm ist."),
                    criterion("Das orange Gespenst dreht sich zu Beginn rechts herum."),
                    criterion("Das orange Gespenst kehrt am des Aufrufs von doMove() seine Drehrichtung um."))
            .build();

    private static final Criterion H1_2_4 = Criterion.builder()
            .shortDescription("H1.2.4 | Rotes Gespenst")
            .maxPoints(3)
            .addChildCriteria(
                    criterion("Das rote Gespenst dreht sich in die Richtung in der sich Pacman befindet."),
                    criterion("Das rote Gespenst dreht weiter nach links, bis es keine Wand mehr vor sich hat."),
                    criterion("Das rote Gespenst bewegt sich ein Feld nach vorne."))
            .build();

    private static final Criterion H1_2 = Criterion.builder()
            .shortDescription("H1.2 | Die Gespenster kommen")
            .addChildCriteria(
                    H1_2_1, H1_2_2, H1_2_3, H1_2_4)
            .build();

    public static final Rubric RUBRIC = Rubric.builder()
            .title("H01 | Pacman")
            .addChildCriteria(
                    H1_1,
                    H1_2)
            .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
