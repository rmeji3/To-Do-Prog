import java.io.File
import java.io.PrintWriter
import java.nio.file.Files

data class Task(var task: String = "No Task Yet", var date : String = "9999")
{

    fun splitMonth() : String
    {
        return date.substring(0,2)
    }
    fun splitDay(): String {
        return date.substring(2,4)
    }


}
fun displayTasks(taskList: MutableList<Task> = mutableListOf())
{
    println("These are your tasks to do!\n")
    var count : Int = 1
    for(t in taskList)
    {
        println(count.toString() + ". your task is: " + t.task)
        println("This is due " +  t.splitMonth() + "/" + t.splitDay()+"\n")
        count++
    }
}
fun removeTask(taskList: MutableList<Task> = mutableListOf())
{

    var toDelete : Int = 0
    while(toDelete != -1)
    {

        displayTasks(taskList)
        print("Enter number to delete task or -1 to exit to menu: ")
        toDelete = readlnOrNull()!!.toInt()
        println()
        println("deleting " + taskList[toDelete-1].task + "......")
        taskList.removeAt(toDelete-1)
        taskList.sortBy{it.date}

    }

}

fun addToList(taskList: MutableList<Task> = mutableListOf())
{
    while (true) {
        print("Enter \"exit\" to quit to menu or the task you want to add: ")
        val taskString: String = readlnOrNull().toString()
        println()
        if (taskString == "exit")
            break
        println("Enter the date in MM/DD")
        print("Month: ")

        val month: String = readlnOrNull().toString()
        println()
        print("Day: ")

        val day: String = readlnOrNull().toString()
        println()
        val date: String = month + day

        var currentTask = Task(taskString, date)
        taskList.add(currentTask)
        taskList.sortBy { it.date }
        displayTasks(taskList)
    }
}
fun completeTask(taskList: MutableList<Task> = mutableListOf()): Int{
    var points : Int = 0
    var toDelete : Int = 0
        while (true) {
            displayTasks(taskList)
            print("Select a task to mark as complete or -1 to exit to menu: ")
            toDelete = readLine()!!.toInt()
            if (toDelete == -1)
                break
            points++
            println("Completed " + taskList[toDelete-1].task + "!")
            println("+1 points :3")
            taskList.removeAt(toDelete - 1)
            taskList.sortBy{it.date}
            println("Points: $points")
        }
    return points
}
fun outFile( taskList: MutableList<Task> = mutableListOf()) {
    val file = File("test.txt")
    val printWriter = PrintWriter(file)
    var count :Int = 1
    printWriter.print("Tasks:\n")
    for (t in taskList) {
        printWriter.print("${count}. " + t.task +"\t"+ t.splitMonth() + "/" + t.splitMonth() + "\n")
        count++
    }

    printWriter.close()
}

fun main() {
    println("Welcome to the To-Do-App! :)")
    //get task
    // get month as string
    // get day as string
    // concatenate month+day then store into date

    var taskList: MutableList<Task> = mutableListOf()
    var points : Int = 0
    while(true) {


        println("Your Points: $points")
        if(taskList.isEmpty())
            println("You Have no Tasks\n")
        else
            displayTasks(taskList)

        println("Type \"A\" to add to your List of Tasks ")
        println("Type \"R\" to Remove a Task ")
        println("Type \"C\" to Complete a Task ")
        println("Type \"E\" to close program ")
        print("choice: ")
        var userChoice = readlnOrNull()?.get(0)
        println()
        userChoice = userChoice?.uppercaseChar()
        when(userChoice)
        {
            'A' -> addToList(taskList)
            'R' -> removeTask(taskList)
            'C' -> points += completeTask(taskList)
            'E' -> break
            else -> println("invalid input")
        }
    }
    outFile(taskList)
    println("Exiting the To-Do-App! :,(")
}