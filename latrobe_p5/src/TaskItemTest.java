import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskItemTest {

    @Test
    public void creatingTaskItemFailsWithInvalidDueDate(){
        assertThrows(InvalidDateException.class, () -> new TaskItem("task","","09-11-2001"));
    }

    @Test
    public void creatingTaskItemFailsWithInvalidTitle(){
        assertThrows(InvalidTitleException.class, () -> new TaskItem("","","2001-09-11"));
    }

    @Test
    public void creatingTaskItemSucceedsWithValidDueDate(){
        String date = "2001-09-11";
        TaskItem task = new TaskItem("title","description",date);
        assertEquals(date,task.getDueDate());
    }

    @Test
    public void creatingTaskItemSucceedsWithValidTitle(){
        String title = "tasktitle";
        TaskItem task = new TaskItem(title,"description","2001-09-11");
        assertEquals(title,task.getTitle());
    }

    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate(){
        String date1 = "2001-09-10";
        String date2 = "2001-09111";
        TaskItem task = new TaskItem("bananas", "eat bananas", date1);
        task.setDueDate(date2);
        assertEquals(date1, task.getDueDate());
    }

    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate(){
        String date1 = "2001-09-10";
        String date2 = "2001-09-11";
        TaskItem task = new TaskItem("bananas", "eat bananas", date1);
        task.setDueDate(date2);
        assertEquals(date2, task.getDueDate());
    }

    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle(){
        String title1 = "apples";
        String title2 = "";
        TaskItem task = new TaskItem(title1, "eat bananas", "2001-09-11");
        task.setTitle(title2);
        assertEquals(title1, task.getTitle());
    }

    @Test
    public void settingTaskItemTitleSucceedsWithValidTitle(){
        String title1 = "apples";
        String title2 = "bananas";
        TaskItem task = new TaskItem(title1, "eat bananas", "2001-09-11");
        task.setTitle(title2);
        assertEquals(title2, task.getTitle());
    }

    @Test
    public void settingTaskItemDescriptionSucceeds(){
        String desc1 = "start early";
        String desc2 = "procrastinate";
        TaskItem task = new TaskItem("Programming assignment", desc1, "2020-11-16");
        task.setDescription(desc2);
        assertEquals(desc2, task.getDescription());
    }
}
