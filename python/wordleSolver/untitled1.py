import random
file = open("words.txt",'r', encoding='utf-8')
#file = open("turkce5.txt",'r', encoding='utf-8')
text = file.read()
file.close()

words = text.split("\n")

rndWord = random.choice(words)

print(rndWord)

blacks = list()
oranges = dict()
greens = [None] *5


#greens = dict()
while(True):
    wordle = input("enter the word: ")
    pattern = input("enter the pattern :")
    for i in range(0, 5):
        if  pattern[i] == "b":
            blacks.append(wordle[i])
            
        elif pattern[i] == "o":
            oranges[i] = wordle[i]
            
        elif pattern[i] == "g":
            #greens[pattern[i]] = i
            greens[i] = wordle[i]

    suggestion ="" 
    for x in words:
        flag = True
        for i in range(0,5):
            if x[i].lower() in blacks and x[i] not in greens and x[i] not in oranges.values():  
                flag = False
                break
            if flag:    
                if(greens[i] != None and x[i].lower() != greens[i]):  
                    flag = False
                    break
            if flag:    
                for key in oranges:
                    if oranges.get(key) not in x.lower():
                        flag = False
                        break
                        
                    else:
                        if key == i and oranges.get(key) == x[i].lower():
                            flag = False
                            break
                    
                                
        if flag: print(x)      
    """for x in words:
        print(x)"""
                    
            
    

