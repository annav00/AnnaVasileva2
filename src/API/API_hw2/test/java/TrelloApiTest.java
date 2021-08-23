import constants.Length;
import constants.ResponseStatus;
import core.TrelloServiceObj;
import beans.TrelloBoard;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class TrelloApiTest {
    @Test
    public void createNewBoardsTest() {
        TrelloBoard board = TrelloServiceObj.createBoard(RandomStringUtils.randomAlphabetic(Length.NAME), ResponseStatus.GOOD_RESPONSE);

        assertThat(board.getClosed(), equalTo(false));
        assertThat(board, is(not(nullValue())));
        TrelloServiceObj.deleteBoard(board.getId(), ResponseStatus.GOOD_RESPONSE);
    }

    @Test
    public void createBoardsWithSameNamesTest() {
        String name = RandomStringUtils.randomAlphabetic(Length.NAME);
        TrelloBoard board1 = TrelloServiceObj.createBoard(name, ResponseStatus.GOOD_RESPONSE);
        TrelloBoard board2 = TrelloServiceObj.createBoard(name, ResponseStatus.GOOD_RESPONSE);

        assertThat(board1.equals(board2), equalTo(false));
        TrelloServiceObj.deleteBoard(board1.getId(), ResponseStatus.GOOD_RESPONSE);
        TrelloServiceObj.deleteBoard(board2.getId(), ResponseStatus.GOOD_RESPONSE);
    }

    @Test
    public void deleteBoardTest() {
        TrelloBoard board = TrelloServiceObj.createBoard(RandomStringUtils.randomAlphabetic(Length.NAME), ResponseStatus.GOOD_RESPONSE);

        TrelloServiceObj.deleteBoard(board.getId(), ResponseStatus.GOOD_RESPONSE);
        TrelloServiceObj.getBoard(board.getId(), ResponseStatus.NOT_FOUND_RESPONSE);
    }

    @Test
    public void closeBoardTest() {
        TrelloBoard board = TrelloServiceObj.createBoard(RandomStringUtils.randomAlphabetic(Length.NAME), ResponseStatus.GOOD_RESPONSE);
        board = TrelloServiceObj.updateBoard(board.getId(), "closed", "true", ResponseStatus.GOOD_RESPONSE);

        assertThat(board.getClosed(), equalTo(true));
        TrelloServiceObj.deleteBoard(board.getId(), ResponseStatus.GOOD_RESPONSE);
    }

    @Test
    public void closeNonExistentBoardTest() {
        TrelloBoard board = TrelloServiceObj.updateBoard(RandomStringUtils.randomAlphanumeric(Length.ID), "closed", "true", ResponseStatus.BAD_RESPONSE);
        assertThat(board, is(nullValue()));
    }

    @Test
    public void deleteNonExistentBoardTest() {
        TrelloBoard board = TrelloServiceObj.deleteBoard(RandomStringUtils.randomAlphanumeric(Length.ID), ResponseStatus.BAD_RESPONSE);
    }

    @Test
    public void getNonExistentBoardTest() {
        TrelloBoard board = TrelloServiceObj.getBoard(RandomStringUtils.randomAlphanumeric(Length.ID), ResponseStatus.BAD_RESPONSE);
        assertThat(board, is(nullValue()));
    }

    @Test
    public void updateDescription() {
        String boardDesc = RandomStringUtils.randomAlphabetic(Length.NAME);
        TrelloBoard board = TrelloServiceObj.createBoard(RandomStringUtils.randomAlphabetic(Length.NAME), ResponseStatus.GOOD_RESPONSE);

        board = TrelloServiceObj.updateBoard(board.getId(), "desc", boardDesc, ResponseStatus.GOOD_RESPONSE);

        assertThat(board.getDesc(), containsString(boardDesc));

        TrelloServiceObj.deleteBoard(board.getId(), ResponseStatus.GOOD_RESPONSE);
    }
}
