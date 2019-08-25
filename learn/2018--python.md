# 2018--python

标签（空格分隔）： python log

---

>**字符串**
    
    title()以首字母大写显示每个单词.同理有upper(),lower().
    strip()/lstrip()/rstrip() 字符串 前后/前/后 去空白
    print('Hello '+ 23 +' rd Birthady!')在python中是错误的.要用str(23)对非字符串进行转型后输出.
    
>**列表**

    定义: list = ['a','b','c']
    末尾添加: list.append('d')
    插入元素: list.insert(1,'e')
    删除元素: del list[1]
    弹出末尾元素: list.pop()
    弹出指定元素: list.pop(1)
    根据值删除元素: list.remove('a') .有重复时只删除第一个元素
    
    //组织列表:
    使用list.sort()对列表进行永久性排序
    list.sort(reverse=True)进行反向排序  true首字母大写
    使用sorted(list)对列表进行临时排序,同样支持反向.
    list.reverse()列表元素反转.
    len(list)确定列表长度
    
    //for循环:
    for alpha in list:
        print(alpha)
        
    //使用函数range():
    for value in range(1,5):
        print(value)
        
    使用range()创建数字列表: nums = list(range(1,6))
    range()函数可指定步长:range(2,11,2)
    
    list=[]
    for value in range(1,11):
	    list.append(value**2) //在python中**表示乘方运算.
    print(list)
    简化版:list=[value**2 for value in range(1,11)]
    
    //对数字列表执行简单的统计计算:
    min(list)/max(list)/sum(list)
    
    //使用列表的一部分--切片
    list = [1,2,3,4,5,6]
    print(list[0:3])/print(list[:3])/print(list[0:])
    如果需要输出后三位元素:print(list[-3:])
    
    使用切片复制列表:list2=list[:],这种复制不是引用而是新建.
    
>**元组**

    元组是不可变的列表: dimensions=(200,50)
    
    //修改元组变量: 虽然不能修改元组的元素,但可以给存储元组的变量赋值
    dimensions=(400,100)
    
>**if语句**

```python
cars = ['audi','bmw','subaru','toyota']
for car in cars:
    if car == 'bmw':
        print(car.upper())
    else:
        print(car.title())

#检查多个条件用: and/or
#检查特定值是否包含在列表中: in
#检查特定值是否不包含在列表中: not in

age = 12
if age < 4:
    print("Your admission cost is $0.")
elif age < 18:
    print("Your admission cost is $5.")
else:
    print("Your admission cost is $10.")

//列表为空=false,不为空=true
list = []
if list:
    print('a')
else:
    print('b')
```

>**字典**
```python
alien_0 = {'color': 'green', 'points': 5}
print(alien_0['color'])
print(alien_0['points'])

#添加键值对
alien_0['x_position'] = 0
alien_0['y_position'] = 25
print(alien_0)

#删除键值对
del alien_0['points']
print(alien_0)

#遍历字典
user_0 = {
    'username': 'efermi',
    'first': 'enrico',
    'last': 'fermi',
}
for key, value in user_0.items():
print("\nKey: " + key)
print("Value: " + value)

#遍历字典中的所有键: 
user_0.keys()

#按顺序遍历字典中的所有键
favorite_languages = {
    'jen': 'python',
    'sarah': 'c',
    'edward': 'ruby',
    'phil': 'python',
}
for name in sorted(favorite_languages.keys()):
    print(name.title() + ", thank you for taking the poll.")
    
#遍历字典中的所有值:
user_0.values()

#通过set()可以返回不重复的一个set集合
favorite_languages = {
    'jen': 'python',
    'sarah': 'c',
    'edward': 'ruby',
    'phil': 'python',
}
print("The following languages have been mentioned:")
for language in set(favorite_languages.values()):
    print(language.title())
    
#嵌套
# 创建一个用于存储外星人的空列表
aliens = []
# 创建 30 个绿色的外星人
for alien_number in range(30):
new_alien = {'color': 'green', 'points': 5, 'speed': 'slow'}
aliens.append(new_alien)
# 显示前五个外星人
for alien in aliens[:5]:
print(alien)
print("...")
# 显示创建了多少个外星人
print("Total number of aliens: " + str(len(aliens)))

#列表中存储字典
#字典中存储列表
#字典中存储字典
```

>**用户输入和while循环**

```python
#函数 input() 让程序暂停运行,等待用户输入一些文本。获取用户输入后, Python 将其存储在一个变量中,以方便你使用。
message = input("Tell me something, and I will repeat it back to you: ")
print(message)

#int()可将字符串转换为数字
print(int(message))

#while循环
prompt = "\nTell me something, and I will repeat it back to you:"
prompt += "\nEnter 'quit' to end the program. "
message = ""
while message != 'quit':
    message = input(prompt)
    print(message)

#break
prompt = "\nPlease enter the name of a city you have visited:"
prompt += "\n(Enter 'quit' when you are finished.) "
while True:
    city = input(prompt)
    if city == 'quit':
        break
    else:
        print("I'd love to go to " + city.title() + "!")

#continue        
current_number = 0
while current_number < 10:
current_number += 1
if current_number % 2 == 0:
continue
print(current_number)

#使用while循环来处理列表和字典
#for 循环是一种遍历列表的有效方式,但在 for 循环中不应修改列表,否则将导致 Python 难以跟踪其中的元素。要在遍历列表的同时对其进行修改,可使用 while 循环。通过
将 while 循环同列表和字典结合起来使用,可收集、存储并组织大量输入,供以后查看和显示。

# 首先,创建一个待验证用户列表
# 和一个用于存储已验证用户的空列表
unconfirmed_users = ['alice', 'brian', 'candace']
confirmed_users = []
# 验证每个用户,直到没有未验证用户为止
# 将每个经过验证的列表都移到已验证用户列表中
while unconfirmed_users:
    current_user = unconfirmed_users.pop()
    print("Verifying user: " + current_user.title())
    confirmed_users.append(current_user)
# 显示所有已验证的用户
print("\nThe following users have been confirmed:")
for confirmed_user in confirmed_users:
    print(confirmed_user.title())

#删除包含特定值的所有列表元素
pets = ['dog', 'cat', 'dog', 'goldfish', 'cat', 'rabbit', 'cat']
print(pets)
while 'cat' in pets:
pets.remove('cat')
print(pets)

#使用用户输入来填充字典
responses = {}
# 设置一个标志,指出调查是否继续
polling_active = True
while polling_active:
    # 提示输入被调查者的名字和回答
    name = input("\nWhat is your name? ")
    response = input("Which mountain would you like to climb someday? ")
    # 将答卷存储在字典中
    responses[name] = response
    # 看看是否还有人要参与调查
    repeat = input("Would you like to let another person respond? (yes/ no) ")
    if repeat == 'no':
        polling_active = False
# 调查结束,显示结果
print("\n--- Poll Results ---")
for name, response in responses.items():
    print(name + " would like to climb " + response + ".")
    
```

---

>**函数**

```python
#定义函数
def greet_user():
    """ 显示简单的问候语 """  #函数文档
    print("Hello!")
greet_user()

#向函数传递信息
def greet_user(username):
    """ 显示简单的问候语 """
    print("Hello, " + username.title() + "!")
greet_user('jesse')

#位置实参:调用函数时:参数与函数定义一一对应.
#关键字实参:顺序无关紧要
def describe_pet(animal_type, pet_name):
    """ 显示宠物的信息 """
    print("\nI have a " + animal_type + ".")
    print("My " + animal_type + "'s name is " + pet_name.title() + ".")
describe_pet(animal_type='hamster', pet_name='harry')

#默认值: 有默认值的参数放在函数的没默认值参数的后面
#但是存在多个默认值时,依然会出现不能对应的问题??
#这个问题可以通过关键字实参来解决
def describe_pet(pet_name, animal_type='dog'):
    """ 显示宠物的信息 """
    print("\nI have a " + animal_type + ".")
    print("My " + animal_type + "'s name is " + pet_name.title() + ".")
describe_pet(pet_name='willie')

#返回值
def get_formatted_name(first_name, last_name):
    """ 返回整洁的姓名 """
    full_name = first_name + ' ' + last_name    
    return full_name.title()
musician = get_formatted_name('jimi', 'hendrix')
print(musician)

#让实参变为可选的---Python将非空字符串解读为True
def get_formatted_name(first_name, last_name, middle_name=''):
    """ 返回整洁的姓名 """
    if middle_name:
        full_name = first_name + ' ' + middle_name + ' ' + last_name
    else:
        full_name = first_name + ' ' + last_name
    return full_name.title()
musician = get_formatted_name('jimi', 'hendrix')
print(musician)
musician = get_formatted_name('john', 'hooker', 'lee')
print(musician)

#通过这个方法可以知道: ''是对应False,' '是对应True
print(bool(''))
print(bool(' '))

#返回字典
#传递列表
#在函数中修改列表--在函数中对这个列表做的修改都是永久性的.
#禁止函数修改列表: 在调用函数时实参设置为list[:],通过切片传递列表的副本


#传递任意数量的实参:
def make_pizza(*toppings):
    """ 打印顾客点的所有配料 """
    print(toppings)
make_pizza('pepperoni')
make_pizza('mushrooms', 'green peppers', 'extra cheese')
#形参名*toppings中的星号让Python创建一个名为toppings的空元组,并将收到的所有值都封装到这个元组中.函数体内的print语句通过生成输出来证明Python能够处理使用一个值调用函数的情形,也能处理使用三个值来调用函数的情形.它以类似的方式处理不同的调用.

def make_pizza(*toppings):
    """ 概述要制作的比萨 """
    print("\nMaking a pizza with the following toppings:")
    for topping in toppings:
        print("- " + topping)
        
make_pizza('pepperoni')
make_pizza('mushrooms', 'green peppers', 'extra cheese')

#结合使用位置实参和任意数量实参
#如果要让函数接受不同类型的实参,必须在函数定义中将接纳任意数量实参的形参放在最后.Python先匹配位置实参和关键字实参,再将余下的实参都收集到最后一个形参中.

#使用任意数量的关键字实参
def build_profile(first, last, **user_info):
    """ 创建一个字典,其中包含我们知道的有关用户的一切 """
    profile = {}
    profile['first_name'] = first
    profile['last_name'] = last
    for key, value in user_info.items():
        profile[key] = value
    return profile
    
user_profile = build_profile('albert', 'einstein',
                            location='princeton',
                            field='physics')
print(user_profile)

#将函数存储在模块中
#将函数存储在被称为模块的独立文件中,再将模块导入在主程序中.import语句允许在当前运行的程序文件中使用模块中的代码.

#导入整个模块: 模块是扩展名为.py的文件.在pizza.py中定义一个函数,在其他.py文件中前面输入有import pizza 就可以调用该函数.
pizza.make_pizza(12, 'mushrooms', 'green peppers', 'extra cheese')
#调用时需要在函数名前添加包名

#导入特定的函数: 调用不需要添加包名
from module_name import function_name
#通过用逗号分隔函数名,可根据需要从模块中导入任意数量的函数:
from module_name import function_0,function_1,function_2

#使用as给模块指定别名: 
import pizza as p

#导入模块中的所有函数: 
from pizza import *

#然而,使用并非自己编写的大型模块时,最好不要采用这种导入方法:如果模块中有函数的名称与你的项目中使用的名称相同,可能导致意想不到的结果: Python 可能遇到多个名称相同的函数或变量,进而覆盖函数,而不是分别导入所有的函数。

#最佳的做法是,要么只导入你需要使用的函数,要么导入整个模块并使用句点表示法。这能让代码更清晰,更容易阅读和理解。

#函数编写指南: 
#1. 给形参指定默认值时,等号两边不要有空格.
#2. 对于函数调用中的关键字实参,也应遵循这种约定.
```

---

##**9.0 类**

###**9.1 创建和使用类**
```python
class Dog():
    """ 一次模拟小狗的简单尝试 """
    
    def __init__(self, name, age):
    """ 初始化属性 name 和 age"""
        self.name = name
        self.age = age
        
    def sit(self):
    """ 模拟小狗被命令时蹲下 """
    print(self.name.title() + " is now sitting.")
    
    def roll_over(self):
    """ 模拟小狗被命令时打滚 """
    print(self.name.title() + " rolled over!")
```
>**方法\__init__()**

* 这是一个特殊的方法,每当你根据Dog类创建新实例时,Python都会自动运行它.名称前后的两个下划线是一种约定,为了避免Python默认方法与普通方法发生名称冲突.
- 方法\__init__()定义了三个形参: self,name,age.这个方法定义中,self必不可少,且必须位于其他形参前面.Python创建Dog实例时,将自动传入实参self.每个与类相关联的方法调用都自动传递实参self,它是一个指向实例本身的引用,让实例能够访问类中的属性和方法.
- 我们创建 Dog 实例时, Python 将调用 Dog 类的方法 \__init__() 。我们将通过实参向 Dog() 传递名字和年龄; self 会自动传递,因此我们不需要传递它。每当我们根据 Dog 类创建实例时,都只需给最后两个形参( name 和 age )提供值。

>**根据类创建实例**

>**继承**
```python
class Car():
    """ 一次模拟汽车的简单尝试 """
    def __init__(self, make, model, year):
        self.make = make
        self.model = model
        self.year = year
        self.odometer_reading = 0

    def get_descriptive_name(self):
        long_name = str(self.year) + ' ' + self.make + ' ' + self.model
        return long_name.title()

    def read_odometer(self):
        print("This car has " + str(self.odometer_reading) + " miles on it.")

    def update_odometer(self, mileage):
        if mileage >= self.odometer_reading:
            self.odometer_reading = mileage
        else:
            print("You can't roll back an odometer!")

    def increment_odometer(self, miles):
        self.odometer_reading += miles

class ElectricCar(Car):
    """ 电动汽车的独特之处 """
    def __init__(self, make, model, year):
        """ 初始化父类的属性 """
        super().__init__(make, model, year)
        
my_tesla = ElectricCar('tesla', 'model s', 2016)
print(my_tesla.get_descriptive_name())
```

>**将实例用作属性**
```python
class Car():
    """ 一次模拟汽车的简单尝试 """
    def __init__(self, make, model, year):
        self.make = make
        self.model = model
        self.year = year
        self.odometer_reading = 0

    def get_descriptive_name(self):
        long_name = str(self.year) + ' ' + self.make + ' ' + self.model
        return long_name.title()

    def read_odometer(self):
        print("This car has " + str(self.odometer_reading) + " miles on it.")

    def update_odometer(self, mileage):
        if mileage >= self.odometer_reading:
            self.odometer_reading = mileage
        else:
            print("You can't roll back an odometer!")

    def increment_odometer(self, miles):
        self.odometer_reading += miles

class Battery():
	def __init__(self,battery_size):
		self.battery_size=battery_size
		
	def describe_battery(self):
		print('This  car has a '+str(self.battery_size)+'-KWh battery.')
		
class ElectricCar(Car):
	def __init__(self,make,model,year,battery):
		super().__init__(make,model,year)
		self.battery=battery

my_tesla=ElectricCar('tesla','model s',2016,Battery(50))
print(my_tesla.get_descriptive_name())
my_tesla.battery.describe_battery()
```

###**导入类**
```python
#导入单个类
from car import Car

#从一个模块中导入多个类
from car import Car,ElectricCar

#导入整个模块
import car

#导入模块的所有类--不推荐
from car import *
```

###**Python标准库**
>**模块collections中的一个类----OrderedDict**

    字典让你能够将信息关联起来,但它们不记录你添加键 — 值对的顺序。要创建字典并记录其中的键 — 值对的添加顺序,可使用模块 collections 中的 OrderedDict类。 OrderedDict实例的行为几乎与字典相同,区别只在于记录了键 — 值对的添加顺序。
    
```python
from collections import OrderedDict

favorite_languages = OrderedDict()

favorite_languages['jen'] = 'python'
favorite_languages['sarah'] = 'c'
favorite_languages['edward'] = 'ruby'
favorite_languages['phil'] = 'python'
for name, language in favorite_languages.items():
    print(name.title() + "'s favorite language is " +
        language.title() + ".")
```


##**文件和异常**

###**从文件中读取数据**
```python
#普通写法是 file = open('pi.txt'),用with...的写法类似java中的try-with-resource的自动关闭
with open('pi_digits.txt') as file_object:
    contents = file_object.read()
    print(contents)
```
相比于原始文件,该输出唯一不同的地方是末尾多了一个空行。为何会多出这个空行呢?因为 read() 到达文件末尾时返回一个空字符串,而将这个空字符串显示出来时就是一个空行。要删除多出来的空行,可在 print 语句中使用 rstrip() .

>**逐行读取**
```python
#逐行读取每次都会读取到一个换行符,print本身也有一个换行符,所以要rstrip().
filename = 'pi_digits.txt'
with open(filename) as file_object:
    for line in file_object:
        print(line.rstrip())
```

>**创建一个包含文件各行内容的列表**
使用关键字 with 时, open() 返回的文件对象只在 with 代码块内可用。如果要在 with 代码块外访问文件的内容,可在 with 代码块内将文件的各行存储在一个列表中,并
在 with 代码块外使用该列表:你可以立即处理文件的各个部分,也可推迟到程序后面再处理。
下面的示例在 with 代码块中将文件 pi_digits.txt 的各行存储在一个列表中,再在 with 代码块外打印它们:
```python
#readlines()从文件中读取每一行,并将其存储在一个列表中.
filename = 'pi_digits.txt'
with open(filename) as file_object:
    lines = file_object.readlines()
for line in lines:
    print(line.rstrip())
```
**注意: 读取文本文件时, Python将其中的所有文本都解读为字符串。如果你读取的是数字,并要将其作为数值使用,就必须使用函数 int() 将其转换为整数,或使用函数 float() 将其转换为浮点数。**

###**写入文件**

>**写入空文件**
```python
#在这个示例中,调用 open()时提供了两个实参(见❶)。第一个实参也是要打开的文件的名称;第二个实参( 'w' )告诉 Python ,我们要以写入模式 打开这个文件。打开文件时,可指定读取模式 ( 'r' )、写入模式 ( 'w' )、附加模式 ( 'a' )或让你能够读取和写入文件的模式( 'r+' )。如果你省略了模式实参,Python 将以默认的只读模式打
开文件。
如果你要写入的文件不存在,函数 open() 将自动创建它。然而,以写入( 'w' )模式打开文件时千万要小心,因为如果指定的文件已经存在, Python 将在返回文件对象前清空
该文件。
file = "programming.txt"
with open(file,'w') as file_:
    file_.write('I love programming.')
```

>**写入多行**
**函数write()不会在你写入的文本末尾添加换行符,因此如果写入多行时要换行符.**

>**附加到文件**
**通过附加模式'a',可以在文件末尾添加文件**
```python
filename='/home/qezhhnjy/python.txt'
with open(filename,'a') as file_:
	file_.write('I also love finding meaning in large datasets.\n')
	file_.write('I love creating apps that can run in a browse.\n')
with open(filename) as file_:
	print(file_.read())
```
###**异常**
>**处理ZeroDivisionError异常**
```python
print(5/0)
#异常内容:
Traceback (most recent call last):
File "division.py", line 1, in <module>
print(5/0)
eroDivisionError: division by zero
```

>**使用try-except代码块**
```python
try:
    print(5/0)
except ZeroDivisionError:
    print("You can't divide by zero!")
```

>**else代码块**
**依赖于try代码块成功执行的代码都应放到else中:**
```python
print("Give me two numbers, and I'll divide them.")
print("Enter 'q' to quit.")
while True:
    first_number = input("\nFirst number: ")
    if first_number == 'q':
        break
    second_number = input("Second number: ")
    try:
        answer = int(first_number) / int(second_number)
    except ZeroDivisionError:
    print("You can't divide by 0!")
    else:
        print(answer)
```
try-except-else 代码块的工作原理大致如下: Python 尝试执行 try代码块中的代码;只有可能引发异常的代码才需要放在 try 语句中。有时候,有一些仅在 try 代码块成功执行时才需要运行的代码;这些代码应放在 else 代码块中。 except 代码块告诉 Python ,如果它尝试运行 try 代码块中的代码时引发了指定的异常,该怎么办。

 >**处理FileNotFoundError异常**
 **'r'模式时,如果文件不存在,会报这个异常.'w'/'a'/'r+'则不会.**
 
 >**分析文本**
 **我们将使用方法split(),它根据一个字符串创建一个单词列表.
 ```python
>>> title = "Alice in Wonderland"
>>> title.split()
['Alice', 'in', 'Wonderland']
```
方法split()以空格为分隔符将字符串分拆为多个部分,并将这些部分都存储到一个列表中.

>**失败时一声不吭**
```python
except FileNotFoundError:
    pass
```

###**存储数据**

很多程序都要求用户输入某种信息,如让用户存储游戏首选项或提供要可视化的数据。不管专注的是什么,程序都把用户提供的信息存储在列表和字典等数据结构中。用户关闭
程序时,你几乎总是要保存他们提供的信息;一种简单的方式是使用模块 json 来存储数据。
模块 json 让你能够将简单的 Python 数据结构转储到文件中,并在程序再次运行时加载该文件中的数据。你还可以使用 json 在 Python 程序之间分享数据。更重要的是, JSON 数据
格式并非 Python 专用的,这让你能够将以 JSON 格式存储的数据与使用其他编程语言的人分享。这是一种轻便格式,很有用,也易于学习。

>**使用json.dump()和json.load()**
    
```python
import json
numbers = [2,3,5,7,11,13]
filename='numbers.json'
with open(filename,'w') as obj:
    json.dump(numbers,obj)
    
with open(filename) as obj:
    num=json.load(obj)
print(num)
```
    
##**测试代码**
```python
import unittest

def get_formatted_name(first,last):
    return (first+' '+last).title()

print("Enter 'q' at any time to quit.")
while True:
    first=input('\nPlease give me a first name.')
    if first == 'q':
        break
    last=input('Please give me a last name')
    if last == 'q':
        break
    formatted_name = get_formatted_name(first,last)
    print('\tNeatly formatted name: '+formatted_name + '.')
    
#单元测试和测试用例
class NamesTestCase(unittest.TestCase):
    """测试get_formatted_name"""
    def test_first_last_name(self):
        formatted_name = get_formatted_name('janis','joplin')
        self.assertEqual(formatted_name,'Janis Joplin')
        
unittest.main()
```

###**测试类**

>**各种断言方法**
unittest.TestCase中的断言方法
|方法|用途|
|-|-|
|`assertEqual(a,b)`|核实 a == b|
|`assertNotEqual(a,b)`|核实 a != b|
|`assertTrue(x)`|核实x为True|
|`assertFalse(x)`|核实x为False|
|`assertIn(item,list)`|核实item在list中|
|`assertNotIn(item,list)`|核实item不在list中|

>**方法 setUp()**
在前面的 test_survey.py 中,我们在每个测试方法中都创建了一个 AnonymousSurvey 实例,并在每个方法中都创建了答案。 unittest.TestCase 类包含方法 setUp() ,让我
们只需创建这些对象一次,并在每个测试方法中使用它们。如果你在 TestCase 类中包含了方法 setUp() , Python 将先运行它,再运行各个以 test_ 打头的方法。这样,在你编写
的每个测试方法中都可使用在方法 setUp() 中创建的对象了。

```python
class AnonymousSurvey():
    """ 收集匿名调查问卷的答案 """
    def __init__(self, question):
    """ 存储一个问题,并为存储答案做准备 """
        self.question = question
        self.responses = []
    def show_question(self):
    """ 显示调查问卷 """
        print(question)
    def store_response(self, new_response):
    """ 存储单份调查答卷 """
        self.responses.append(new_response)
    def show_results(self):
    """ 显示收集到的所有答卷 """
        print("Survey results:")
        for response in responses:
            print('- ' + response)
```

```python
import unittest
from survey import AnonymousSurvey

class TestAnonymousSurvey(unittest.TestCase):
    """针对AnonymousSurvey类的测试"""
    
    def setUp(self):
        """创建一个调查对象和一组答案,供使用的测试方法使用"""
        question = 'What language did you first learn to speak?'
        self.my_survey=AnonymousSurvey(question)
        self.responses = ['English','Spanish','Manadarin']
        
    def test_store_single_response(self):
        """测试单个答案会被妥善存储"""
        self.my_survey.store_response(self.responses[0])
        self.assertIn(self.responses[0],self.my_survey.responses)
        
    def test_store_three_responses(self):
        """测试三个答案会被妥善存储"""
        for response in self.responses:
            self.my_survey.store_response(response)
        for response in self.responses:
            self.assertIn(response,self.my_survey.responses)
```

---

#**项目**

##**项目1--外星人入侵**

先安装pip,然后通过pip安装pygame--对应python2.7,如果是python3要装一些依赖,反正我没成功.
1. https://bootstrap.pypa.io/get-pip.py 下载这个.py文件,执行`python get-pip.py`安装pip
2. `pip --version`查看pip版本.
3. `sudo apt-get install python-pygame`安装pygame.
4. 通过`python`进入pyghon,输入`import pygame`,不报错则表示安装pygame成功.

>**开始游戏项目**
```python
# --coding:utf8--
import sys
import pygame

def run_game():
    """初始化并创建一个屏幕对象"""
    pygame.init()
    screen = pygame.display.set_mode((600,400))
    pygame.display.set_caption("Alien Invasion")
    
    #设置背景颜色
    #bg_color = (70,209,60)
    
    """开始游戏的主循环"""
    while True:
        '''监视键盘和鼠标事件'''
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                sys.exit()
        
        #每次循环时都重绘屏幕,如果不设置背景颜色,可以不要这句
        #screen.fill(bg_color)
        #让最近绘制的屏幕可见
        pygame.display.flip()

run_game()
```
