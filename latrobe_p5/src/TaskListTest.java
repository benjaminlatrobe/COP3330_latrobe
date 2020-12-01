import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void addingTaskItemsIncreasesSize(){
        TaskList list = new TaskList();
        assertEquals(0,list.size());
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertEquals(1,list.size());
    }

    @Test
    public void completingTaskItemChangesStatus(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertFalse(list.get(0).getCompletionStatus());
        list.complete(0);
        assertTrue(list.get(0).getCompletionStatus());
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertThrows(InvalidParameterException.class, () -> list.complete(5));
    }

    @Test
    public void editingTaskItemChangesValues(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","unchanged","2001-09-11");
        list.add(task);
        list.get(0).setDescription("changed");
        list.get(0).setDueDate("2031-06-05");
        list.get(0).setTitle("t2");
        assertNotEquals("t",list.get(0).getTitle());
        assertNotEquals("unchanged",list.get(0).getDescription());
        assertNotEquals("2001-09-11",list.get(0).getDueDate());
    }

    @Test
    public void editingTaskItemDescriptionChangesValue(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        list.get(0).setDescription("changed");
        assertEquals("changed", list.get(0).getDescription());
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertThrows(InvalidParameterException.class, () -> list.get(2).setDescription("change?"));
    }

    @Test
    public void editingTaskItemDueDateChangesValue(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        list.get(0).setDueDate("1999-10-20");
        assertEquals("1999-10-20", list.get(0).getDueDate());
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertThrows(InvalidParameterException.class, () -> list.get(2).setDueDate("1999-10-20"));
    }

    @Test
    public void editingTaskItemTitleChangesValue(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        list.get(0).setTitle("t2");
        assertEquals("t2", list.get(0).getTitle());
    }

    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertThrows(InvalidParameterException.class, () -> list.get(2).setTitle("t2"));
    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertThrows(InvalidParameterException.class, () -> list.get(2).getDescription());
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","banana","2001-09-11");
        list.add(task);
        assertEquals("banana", list.get(0).getDescription());
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertThrows(InvalidParameterException.class, () -> list.get(2).getDueDate());
    }

    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertEquals("2001-09-11", list.get(0).getDueDate());
    }

    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertThrows(InvalidParameterException.class, () -> list.get(2).getTitle());
    }

    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertEquals("t", list.get(0).getTitle());
    }

    @Test
    public void newTaskListIsEmpty(){
        TaskList list = new TaskList();
        assertEquals(0,list.size());
    }

    @Test
    public void removingTaskItemsDecreasesSize(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertEquals(1,list.size());
        list.remove(0);
        assertEquals(0,list.size());
    }

    @Test
    public void removingTaskItemsFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertThrows(InvalidParameterException.class, () -> list.remove(2));
    }

    @Test
    public void savedTaskListCanBeLoaded(){
        TaskList savedList = new TaskList();
        TaskItem item = new TaskItem("teach dog new trick", "teach dog to fetch beer", "2020-12-25");
        savedList.add(item);
        savedList.save("save8675309.txt");
        TaskList loadedList = new TaskList();
        loadedList.load("save8675309.txt");
        assertEquals(savedList.get(0).toString(), loadedList.get(0).toString());
    }

    @Test
    public void uncompletingTaskItemChangesStatus(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        list.complete(0);
        assertTrue(list.get(0).getCompletionStatus());
        list.uncomplete(0);
        assertFalse(list.get(0).getCompletionStatus());
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex(){
        TaskList list = new TaskList();
        TaskItem task = new TaskItem("t","","2001-09-11");
        list.add(task);
        assertThrows(InvalidParameterException.class, () -> list.uncomplete(5));
    }

}
