import tkinter  as tk
from tkinter import *
from tkinter import ttk

class Student():
    
    def __init__(self, name, test1:int , test2:int, test3:int):
        
        self.name = name
        if var.get() == 0:
            self.test1 = test1
            self.test2 = test2
            self.test3 = test3
        else:
            self.test1 = self.letterToNumeric(test1)
            self.test2 = self.letterToNumeric(test2)
            self.test3 = self.letterToNumeric(test3)
        
          
    def getNumericAverage(self):
        return round((self.test1 + self.test2 + self.test3)/3.0, 2)
    
    
    def getLetterAverage(self):
        result = 0.0
        tests = [int(self.test1),int(self.test2),int(self.test3)]
        for t in tests:
            if self.numericToLetter(t) == "A": result = result + 4.0
            elif self.numericToLetter(t) == "A-": result = result + 3.76
            elif self.numericToLetter(t) == "B+": result = result + 3.54
            elif self.numericToLetter(t) == "B": result = result + 3.33
            elif self.numericToLetter(t) == "B-": result = result + 3.12
            elif self.numericToLetter(t) == "C+": result = result + 2.90
            elif self.numericToLetter(t) == "C": result = result + 2.69
            elif self.numericToLetter(t) == "C-": result = result + 2.47
            else : result = result + 0
        return round (result / 3.0   ,2)       
    
    def numericToLetter(self,n:int):
        if n > 94: return "A"
        elif n > 89: return "A-"
        elif n > 84: return "B+"
        elif n > 79: return "B"
        elif n > 74: return "B-"
        elif n > 69: return "C+"
        elif n > 64: return "C"
        elif n > 59: return "C-"
        else : return "F"
        
        
    def letterToNumeric(self,s:str()):
        if s == "A": return 100
        elif s == "A-": return 94
        elif s == "B+": return 89
        elif s == "B" :return 84
        elif s == "B-": return 79
        elif s == "C+": return 74
        elif s == "C" :return 69
        elif s == "C-": return 64
        elif s == "F" :return 0
    
    
    
    def getValues(self):
        if var.get() == 0:
            return (self.name,self.test1,self.test2,self.test3,self.getNumericAverage())
        else:
            tests = [self.test1,self.test2,self.test3]
            
            for i in range(0,len(tests)):
                tests[i] = self.numericToLetter(int(tests[i]))
                
            return (self.name,tests[0],tests[1],tests[2],self.getLetterAverage())


def calculateClassAverage():
    if(len(Students) == 0):
        return 0.0
    else:
        total=0.0
        for s in Students:
            if var.get() == 0:
                total = total + s.getNumericAverage()
            else:
                total = total + s.getLetterAverage()
        
    
        return round(total / len(Students) ,2)  

def radioClicked():
    refreshApp()
    
def clearTable():
   for item in tv.get_children():
      tv.delete(item)

def refreshApp():
    studentNameEntry.delete(0,END)
    Test1Entry.delete(0,END)
    Test2Entry.delete(0,END)
    Test3Entry.delete(0,END)
    
    
    
    label = tk.Label(root, text ="                   ",  font = ('Arial',12))
    label.place(x = 630, y = 350)
    
    label = tk.Label(root, text =str(calculateClassAverage()),  font = ('Arial',12))
    label.place(x = 630, y = 350)
        
    clearTable()
    for s in Students:
        tv.insert('', 'end', values = s.getValues())

def submitGrade():
    try:
        flag = True
        if var.get()==0:
            tests = [int(Test1Entry.get()),int(Test2Entry.get()),int(Test2Entry.get())]
            for t in tests:
                if t > 100 or t < 0:
                    tk.messagebox.showerror(title="Error", message= "Please enter valid inputs. Numeric values have to be between 0-100.")
                    flag = False
                    break
            if flag == True:
                Students.append(Student(studentNameEntry.get(), int( Test1Entry.get() ) ,int( Test2Entry.get() ),int( Test3Entry.get())))                
        else:
            tests = [Test1Entry.get(),Test2Entry.get(),Test2Entry.get()]
            
            for t in tests:
                if t != "A" and t != "A-" and t != "B+" and  t != "B" and  t != "B-" and  t != "C+"  and t != "C" and  t != "C-" and t != "F":
                    flag = False
                    tk.messagebox.showerror(title="Error", message= "Please enter valid inputs. Letter values have to be between A-F")
                    break
            if flag == True:    
                Students.append(Student(studentNameEntry.get(),  Test1Entry.get()  ,Test2Entry.get() , Test3Entry.get()))                    
    
    except Exception as e:
        tk.messagebox.showerror(title="Error", message= (e , "Please enter valid inputs") )
        
    
    if flag == True:
        refreshApp()
    
    
root = tk.Tk()
root.geometry("800x400")
root.resizable(False , False)
root.title("Average Calculator")

inputGradeFrame = tk.Frame(root,   highlightbackground="black", highlightthickness=1)
inputGradeFrame.pack()
inputGradeFrame.place( x = 20, y= 20, height = 300, width = 300)


label = tk.Label(root, text ="Input Grade",  font = ('Arial',16))
label.place(x = 40, y = 10)

studentNameLabel = tk.Label(inputGradeFrame, text ="Student Name:",  font = ('Arial',12))
studentNameLabel.place(x = 20,y = 40)

studentNameEntry = tk.Entry(inputGradeFrame)
studentNameEntry.place(x = 150, y = 40)

Test1Label = tk.Label(inputGradeFrame, text ="Test 1:",  font = ('Arial',8))
Test1Label.place(x = 20, y = 100)

Test1Entry = tk.Entry(inputGradeFrame)
Test1Entry.place(x = 150, y = 100 , width = 100)

Test2Label = tk.Label(inputGradeFrame, text ="Test 2:",  font = ('Arial',8))
Test2Label.place(x = 20, y = 130)

Test2Entry = tk.Entry(inputGradeFrame)
Test2Entry.place(x = 150, y = 130 , width = 100)

Test3Label = tk.Label(inputGradeFrame, text ="Test 3:",  font = ('Arial',8))
Test3Label.place(x = 20, y = 160)

Test3Entry = tk.Entry(inputGradeFrame)
Test3Entry.place(x = 150, y = 160 , width = 100)

submitGradesButton = tk.Button(inputGradeFrame, text = "Submit Grades" , font=('Arial',12), command = submitGrade)
submitGradesButton.place(x = 150, y = 230)

tableFrame = tk.Frame(root, highlightbackground="black", highlightthickness=1)
tableFrame.pack()
tableFrame.place(x = 350 , y = 20 , height = 300 , width = 430)

label = tk.Label(root, text ="Average of Each Student:",  font = ('Arial',12))
label.place(x = 370, y = 10)

tableTextBox = tk.Text(tableFrame, width = 45, height = 18 , state = "disabled")
tableTextBox.pack(padx = 10, pady = 20)

tv = ttk.Treeview(tableTextBox, columns = (1,2,3,4,5), show = "headings" )
tv. pack()
tv.column("# 1",anchor=CENTER, stretch=NO, width=70)
tv.heading(1, text = "Name")
tv.column("# 2",anchor=CENTER, stretch=NO, width=70)
tv.heading(2, text = "Test1")
tv.column("# 3",anchor=CENTER, stretch=NO, width=70)
tv.heading(3, text = "Test2")
tv.column("# 4",anchor=CENTER, stretch=NO, width=70)
tv.heading(4, text = "Test3")
tv.column("# 5",anchor=CENTER, stretch=NO, width=70)
tv.heading(5, text = "Average")

Students = []


var = IntVar()
radioNumeric = tk.Radiobutton(root, text = "Numeric",variable = var, value = 0, command = radioClicked)
radioNumeric.pack()
radioNumeric.place(x = 30, y = 350)
radioLetter = tk.Radiobutton(root, text = "Letter", variable = var ,value = 1, command = radioClicked)
radioLetter.pack()
radioLetter.place(x = 100, y = 350)

label = tk.Label(root, text ="Class Average:",  font = ('Arial',12))
label.place(x = 500, y = 350)


root.mainloop()



            
    
            
        


