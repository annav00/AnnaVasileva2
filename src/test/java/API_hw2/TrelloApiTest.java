package API_hw2;

import API_hw2.data.Length;
import API_hw2.data.ResponseStatus;
import API_hw2.core.TrelloServiceObj;
import API_hw2.beans.TrelloBoard;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class TrelloApiTest {
    TrelloBoard board;

    @BeforeMethod(onlyForGroups = {"withBeforeMethod"})
    public void createBoard(){
        board = TrelloServiceObj.createBoard(RandomStringUtils.randomAlphabetic(Length.NAME), ResponseStatus.GOOD_RESPONSE);
    }

    @AfterMethod(onlyForGroups = {"withAfterMethod"})
    public void deleteBoard(){
        TrelloServiceObj.deleteBoard(board.getId(), ResponseStatus.GOOD_RESPONSE);
    }

    @Test(groups = { "withBeforeMethod","withAfterMethod"})
    public void createNewBoardsTest() {
        assertThat(board.getClosed(), equalTo(false));
        assertThat(board, is(not(nullValue())));
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

    @Test(groups = { "withBeforeMethod"})
    public void deleteBoardTest() {
        TrelloServiceObj.deleteBoard(board.getId(), ResponseStatus.GOOD_RESPONSE);
        TrelloServiceObj.getBoard(board.getId(), ResponseStatus.NOT_FOUND_RESPONSE);
    }

    @Test(groups = { "withBeforeMethod","withAfterMethod"})
    public void closeBoardTest() {
       board = TrelloServiceObj.updateBoard(board.getId(), "closed", "true", ResponseStatus.GOOD_RESPONSE);
       assertThat(board.getClosed(), equalTo(true));
    }

    @Test
    public void closeNonExistentBoardTest() {
        TrelloBoard board = TrelloServiceObj.updateBoard(RandomStringUtils.randomAlphanumeric(Length.ID), "closed", "true", ResponseStatus.BAD_RESPONSE);
        assertThat(board, is(nullValue()));
    }

    @Test
    public void deleteNonExistentBoardTest() {
        TrelloServiceObj.deleteBoard(RandomStringUtils.randomAlphanumeric(Length.ID), ResponseStatus.BAD_RESPONSE);
    }

    @Test
    public void getNonExistentBoardTest() {
        TrelloBoard board = TrelloServiceObj.getBoard(RandomStringUtils.randomAlphanumeric(Length.ID), ResponseStatus.BAD_RESPONSE);
        assertThat(board, is(nullValue()));
    }

    @Test(groups = { "withBeforeMethod","withAfterMethod"})
    public void updateDescription() {
        String boardDesc = RandomStringUtils.randomAlphabetic(Length.NAME);
        board = TrelloServiceObj.updateBoard(board.getId(), "desc", boardDesc, ResponseStatus.GOOD_RESPONSE);
        assertThat(board.getDesc(), containsString(boardDesc));
    }
}
