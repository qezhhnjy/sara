

# **CSS**
`css` `菜鸟教程`



## 1. CSS语法
![](http://www.runoob.com/wp-content/uploads/2013/07/632877C9-2462-41D6-BD0E-F7317E4C42AC.jpg)

---
## 2.  CSS Id和Class选择器
>### **id选择器**
```css
#id{
    text-align:center;
    color:red;
}
```

<p style="color:#f23">id属性不要以数字开头，数字开头的id在Mozilla/Firefox 浏览器中不起作用。</p>

>### **class选择器**
```css
    p.center{text-align:center;}
 ```
 <p style="color:red">类名的第一个字符不能使用数字！它无法在Mozilla或Firefox中起作用。</p>

---

## 3. CSS创建
>### **如何插入样式表**

1. 外部样式表(External style sheet)
2. 内部样式表(Internal style sheet)
3. 内联样式(Inline style)

>### **外部样式表**

```html
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css"/>
</head>
```
>### **内部样式表**
```html
<head>
<style>
hr {color:sienna;}
p {margin-left:20px;}
body {background-image:url("images/back40.gif");}
</style>
</head>
```

>### **内联样式**
```html
<p style="color:sienna;margin-left:20px">这是一个段落。</p>
```

>### **多重样式**

如果某些属性在不同的样式表中被同样的选择器定义，那么属性值将从更具体的样式表中被继承过来。

例如，外部样式表拥有针对h3选择器的三个属性：
```css
h3
{
    color:red;
    text-align:left;
    font-size:8pt;
}
```
而内部样式表拥有针对h3选择器的两个属性：
```css
h3
{
    text-align:right;
    font-size:20pt;
}
```
假如拥有内部样式表的这个页面同时与外部样式表链接，那么h3得到的样式是：
```css
color:red;
text-align:right;
font-size:20pt;
```

>### **多重样式优先级**

`Inline style` > `Internal style sheet` > `External style sheet`

<p style="color:red">注意：如果外部样式放在内部样式的后面，则外部样式将覆盖内部样式</p>

---

## 4. CSS background
CSS背景属性用于定义HTML元素的背景。

CSS属性定义背景效果：

|值 |	说明| 	CSS|
|-|-|-|
|`background-color `|	指定要使用的背景颜色| 	1|
|`background-position`| 	指定背景图像的位置 	|1|
|`background-size`| 	指定背景图片的大小 	|3|
|`background-repeat`| 	指定如何重复背景图像 	|1|
|`background-origin`| 	指定背景图像的定位区域 	|3|
|`background-clip`| 	指定背景图像的绘画区域 	|3|
|`background-attachment`| 	设置背景图像是否固定或者随着页面的其余部分滚动 	|1|
|`background-image `|	指定要使用的一个或多个背景图像 	|1|

>### **背景颜色 background-color**
```css
body{background-color:#boc4de;}
```
CSS中，颜色值通常以以下方式定义：
- 十六进制 - 如："#ffddee" "#ffe"
- RGB - ru："rgb(255,0,0)"
- 颜色名称 - 如："red"

>### **背景图像 background-image**
```css
body{background-image:url('paper.gif');}
```

>### **背景图像 - 固定或滚动 background-attachment**

|值|说明|
|-|-|
|scroll|背景图片随页面的其余部分滚动，默认值
|fixed|背景图像是固定的|
|inherit|指定该项的设置从父元素继承|

>### **背景图像 - 水平或垂直平铺**
默认情况下background-image属性会在页面的水平及垂直方向平铺。

设置图像只在水平方向平铺(repeat-x):
```css
body{
    background-image:url('gradient2.png');
    background-repeat:repeat-x;
}
```

>### **背景图像 - 设置定位与不平铺**
```css
body{
    background-image:url('img_tree.png');
    background-repeat:no-repeat;
}
```
利用background-position 属性改变图像在背景中的位置：
```css
body{
    background-image:url('img_tree.png');
    background-repeat:no-repeat;
    background-position:right top;
}
```

>### **background-size**
指定背景图片的大小

- 语法
```css
background-size: length|percentage|cover|contain
```
|值 |	描述|
|-|-|
|`length`| 	设置背景图片高度和宽度。第一个值设置宽度，第二个值设置的高度。如果只给出一个值，第二个是设置为 auto(自动)|
|`percentage`| 	将计算相对于背景定位区域的百分比。第一个值设置宽度，第二个值设置的高度。如果只给出一个值，第二个是设置为"auto(自动)"|
|`cover`| 	此时会保持图像的纵横比并将图像缩放成将完全覆盖背景定位区域的最小大小。|
|`contain`| 	此时会保持图像的纵横比并将图像缩放成将适合背景定位区域的最大大小。|

>### **background-origin**
- 语法
```css
background-origin:padding-box|border-box|content-box;
```

|值 	|描述|
|-|-|
|`padding-box` |背景图像填充框的相对位置
|`border-box`|	背景图像边界框的相对位置|
|`content-box`|	背景图像的相对位置的内容|

>### **background-clip**
- 语法
```css
background-clip:border-box|padding-box|content-box;
```
|值 |	说明|
|-|-|
|`border-box `	|默认值。背景绘制在边框方框内（剪切成边框方框）|
|`padding-box` |	背景绘制在衬距方框内（剪切成衬距方框）|
|`content-box` 	|背景绘制在内容方框内（剪切成内容方框）|

>### **背景 - 简写属性**
```css
body{background:#fff url('img_tree.png') no-repeat right top;}
```
当使用简写属性时，属性值的顺序为：

- background-color
- background-image
- background-repeat
- background-attachment
- background-position

---

## 5. CSS Text
|属性 	|描述|
|-|-|
|`color` 	|设置文本颜色|
|`direction `	|设置文本方向。|
|`letter-spacing` |	设置字符间距|
|`line-height` 	|设置行高|
|`text-align` |对齐元素中的文本|
|`text-decoration` |	向文本添加修饰|
|`text-indent` |	缩进元素中文本的首行|
|`text-shadow` |	设置文本阴影|
|`text-transform`| 	控制元素中的字母|
|`unicode-bidi` |	设置或返回文本是否被重写 |
|`vertical-align`| 	设置元素的垂直对齐|
|`white-space`| 	设置元素中空白的处理方式|
|`word-spacing`| 	设置字间距|

>### **color**
|值 	|描述|
|-|-|
|`color_name` 	|规定颜色值为颜色名称的颜色（比如 red）|
|`hex_number` 	|规定颜色值为十六进制值的颜色（比如 #ff0000）|
|`rgb_number` 	|规定颜色值为 rgb 代码的颜色（比如 rgb(255,0,0)）|
|`inherit` 	|规定应该从父元素继承颜色|

>### **direction**
|值 	|描述|
|-|-|
|`ltr `	|默认。文本方向从左到右|
|`rtl `	|文本方向从右到左|
|`inherit `	|规定应该从父元素继承 direction 属性的值|

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
div.ex1 {direction:rtl;}
</style>
</head>
<body>

<div>一些文本。默认的书写方向。</div>
<div class="ex1">一些文本。 Right-to-left 方向。</div>

</body>
</html>
```

效果：
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
div.ex1 {direction:rtl;}
</style>
</head>
<body>

<div>一些文本。默认的书写方向。</div>
<div class="ex1">一些文本。 Right-to-left 方向。</div>

</body>
</html>

>### **letter-spacing**
增加或减少字符间的空白(字符间距)。

|值 	|描述|
|-|-|
|`normal` 	|默认。规定字符间没有额外的空间。|
|`length` 	|定义字符间的固定空间（允许使用负值）。|
|`inherit` 	|规定应该从父元素继承 letter-spacing 属性的值。|

>### **line-height**
设置文本的行高，不允许负值。

|值 	|描述|
|-|-|
|`normal` 	|默认。设置合理的行间距。|
|`number` |	设置数字，此数字会与当前的字体尺寸相乘来设置行间距。|
|`length` |	设置固定的行间距(px...)。
|`%` |	基于当前字体尺寸的百分比行间距。
|`inherit`| 	规定应该从父元素继承 line-height 属性的值。

>### **text-align**
指定元素文本的水平对齐方式。
|值 	|描述|
|-|-
|`left 	`|把文本排列到左边。默认值：由浏览器决定。
|`right `|	把文本排列到右边。
|`center `|	把文本排列到中间。
|`justify` |	实现两端对齐文本效果。
|`inherit`| 	规定应该从父元素继承 text-align 属性的值。

>### **text-decoration**
规定添加到文本的修饰。

值 	|描述
-|-
`none `	|默认。定义标准的文本。
`underline `|定义文本下的一条线。
`overline `	|定义文本上的一条线。
`line-through `	|定义穿过文本下的一条线。
`blink 	`|定义闪烁的文本，貌似好多浏览器不支持了。
`inherit` 	|规定应该从父元素继承 text-decoration 属性的值。

>### **text-indent**
text-indent 属性规定文本块中首行文本的缩进。

负值是允许的，如果值是负数，将第一行的内容左缩进。

值 |	描述
-|-
`length`| 	定义固定的缩进。默认值：0。
`% `	|定义基于父元素宽度的百分比的缩进。
`inherit` |	规定应该从父元素继承 text-indent 属性的值。

>### **text-shadow** 
`text-shadow` 属性应用于阴影文本。
- 语法
```css
text-shadow: h-shadow v-shadow blur color;
```
值| 	描述
-|-
`h-shadow` |	必需。水平阴影的位置。允许负值。
`v-shadow` |	必需。垂直阴影的位置。允许负值。
`blur `	|可选。模糊的距离。
`color` |	可选。阴影的颜色

>### **text-transform**
`text-transform` 属性控制文本的大小写。

值 	|描述
-|-
`none` |	默认。定义带有小写字母和大写字母的标准的文本。
`capitalize` |	文本中的每个单词以大写字母开头。
`uppercase` |	定义仅有大写字母。
`lowercase` |	定义无大写字母，仅有小写字母。
`inherit` |	规定应该从父元素继承 text-transform 属性的值。

>### **unicode-bidi**
`unicode-bidi` 属性与 `direction` 属性一起使用，来设置或返回文本是否被重写，以便在同一文档中支持多种语言。

- 语法
```css
unicode-bidi: normal|embed|bidi-override|initial|inherit;
```

值| 	描述 
-|-
`normal` 	|默认。不使用附加的嵌入层面。 	
`embed` |	创建一个附加的嵌入层面。 	
`bidi-override` |	创建一个附加的嵌入层面。重新排序取决于 direction 属性。 
`initial` |	设置该属性为它的默认值。 
`inherit` |	从父元素继承该属性。

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title>
<style>
div.ex1
{
	direction:rtl;
	unicode-bidi:bidi-override
}
</style>
</head>
<body>

<div>一些文本。默认的书写方向。</div>
<div class="ex1">一些文本 。从右向左的方向。</div>

</body>
</html>
```
<p style="color:red">上面的css可以实现ex1所有字完全反转过来的效果：</p>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title>
<style>
div.ex1
{
	direction:rtl;
	unicode-bidi:bidi-override
}
</style>
</head>
<body>

<div>一些文本。默认的书写方向。</div>
<div class="ex1">一些文本 。从右向左的方向。</div>

</body>
</html>

>### **vertical-align**
`vertical-align` 属性设置一个元素的垂直对齐方式。

该属性定义行内元素的基线相对于该元素所在行的基线的垂直对齐。允许指定负长度值和百分比值，这会使元素降低而不是升高，在表单元格中，这个属性会设置单元格框中的单元格内容的对齐方式。

值 	|描述
-|-
`baseline` |	默认。元素放置在父元素的基线上。
`sub` |	垂直对齐文本的下标。
`super` |	垂直对齐文本的上标
`top` |	把元素的顶端与行中最高元素的顶端对齐
`text-top` |	把元素的顶端与父元素字体的顶端对齐
`middle` |	把此元素放置在父元素的中部。
`bottom` |	把元素的底端与行中最低的元素的顶端对齐。
`text-bottom` |	把元素的底端与父元素字体的底端对齐。
`length` 	 |
`%` |	使用 "line-height" 属性的百分比值来排列此元素。允许使用负值。
`inherit` |	规定应该从父元素继承 vertical-align 属性的值。

>### **white-space**

`white-space` 属性指定元素内的空白怎样处理。

值 |	描述
-|-
`normal` |	默认。空白会被浏览器忽略。
`pre` |	空白会被浏览器保留。其行为方式类似 HTML 中的 pre 标签。
`nowrap` |	文本不会换行，文本会在在同一行上继续，直到遇到 br 标签为止。
`pre-wrap` |	保留空白符序列，但是正常地进行换行。
`pre-line` |	合并空白符序列，但是保留换行符。
`inherit` |	规定应该从父元素继承 white-space 属性的值。

>### **word-spacing**

`word-spacing`  属性增加或减少字与字之间的空白，允许负值。

值 |	描述
-|-
`normal` |	默认。定义单词间的标准空间。
`length` |	定义单词间的固定空间。
`inherit` |	规定应该从父元素继承 word-spacing 属性的值。

---

## 6. CSS Font

>### **所有CSS字体属性**

Property |	描述
-|-
`font` |	在一个声明中设置所有的字体属性
`font-family` |	指定文本的字体系列
`font-size` |	指定文本的字体大小
`font-style` |	指定文本的字体样式
`font-variant` |	以小型大写字体或者正常字体显示文本。
`font-weight` |	指定字体的粗细。

>### **font-size**
`font-size` 属性设置字体大小。

值 |	描述
-|-
`xx-small` `x-small` `small` `medium` `large` `x-large` `xx-large`|把字体的尺寸设置为不同的尺寸，从 xx-small 到 xx-large。默认值：medium。
`smaller` |	把 font-size 设置为比父元素更小的尺寸。
`larger` |	把 font-size 设置为比父元素更大的尺寸。
`length` 	|把 font-size 设置为一个固定的值。
`%` |	把 font-size 设置为基于父元素的一个百分比值。
`inherit` |	规定应该从父元素继承字体尺寸。

>### **font-style**

`font-style` 属性指定文本的字体样式。
值 |	描述
-|-
`normal` |	默认值。浏览器显示一个标准的字体样式。
`italic` |	浏览器会显示一个斜体的字体样式。
`oblique` |	浏览器会显示一个倾斜的字体样式。
`inherit` |	规定应该从父元素继承字体样式。

>### **font-variant**

`font-variant` 属性设置小型大写字母的字体显示文本，这意味着所有的小写字母均会转换为大写，但是所有使用小型大写字体的字母与其余文本相比，其字体尺寸更小。

值 	|描述
-|-
`normal` |	默认值。浏览器会显示一个标准的字体。
`small-caps` |	浏览器会显示小型大写字母的字体。
`inherit` |	规定应该从父元素继承 font-variant 属性的值。

<p style="font-variant:small-caps">Do Not Go Gentle Into That Good Night

>### **font-weight**

`font-weight` 属性设置文本的粗细。
值 	|描述
-|-
`normal` 	|默认值。定义标准的字符。
`bold` |	定义粗体字符。
`bolder` |	定义更粗的字符。
`lighter` |	定义更细的字符。
`size` | 定义由粗到细的字符。400 等同于 normal，而 700 等同于 bold。
`inherit` |	规定应该从父元素继承字体的粗细。


>### **CSS字型**
在CSS中，有两种类型的字体系列名称：
- **通用字体系列** - 拥有相似外观的字体系统组合( 如 Serif 或 Monospace )
- **特定字体系列** - 一个特定的字体系列 ( 如 Times 或 Courier )

Generic family |	字体系列 |	说明
-|-|-
`Serif` |	`Times New Roman`  , `Georgia` 	|Serif 字体中字符在行的末端拥有额外的装饰
`Sans-serif` |	`Arial`  , `Verdana` 	|"Sans" 是指无 - 这些字体在末端没有额外的装饰
`Monospace` |	`Courier New`  , `Lucida Console` 	|所有的等宽字符具有相同的宽度

>### **字体系列**
`font-family` 属性设置文本的字体系列。

`font-family` 属性应该设置几个字体名称作为一个后备机制，如果浏览器不支持第一种字体，将会尝试下一种字体。

<p style='color:red'>如果字体系列的名称超过一个字，它必须用引号，多个字体系列用逗号分隔。

```css
p{font-family:"Times New Roman", Times, serif;} 
```

>### **CSS Web安全字体组合**

- **常用的字体组合**

`font-family` 属性是多种字体的名称，作为一个应变机制，以确保浏览器/操作系统之间的最大兼容性。如果浏览器不支持第一种字体，则会尝试一种字体。

想要的字体类型如果浏览器找不到，它会从通用的字体类型中找到与你相似的。

- **Serif 字体**

<table class="reference">
<tbody><tr>
<th width="55%" align="left">字体</th>
<th align="left">文本示例</th>
</tr>
<tr>
<td>Georgia, serif</td>
<td><h2 style="margin-top:0px;font-family: Georgia, serif">This is a heading</h2><p style="margin-bottom:0px;font-family: Georgia, serif">This is a paragraph</p></td>
</tr>
<tr>
<td>"Palatino Linotype", "Book Antiqua", Palatino, serif</td>
<td><h2 style="margin-top:0px;font-family: 'Palatino Linotype', 'Book Antiqua', Palatino, serif">This is a heading</h2><p style="margin-bottom:0px;font-family: 'Palatino Linotype', 'Book Antiqua', Palatino, serif">This is a paragraph</p></td>
</tr>
<tr>
<td>"Times New Roman", Times, serif</td>
<td><h2 style="margin-top:0px;font-family: 'Times New Roman', Times, serif">This is a heading</h2><p style="margin-bottom:0px;font-family: 'Times New Roman', Times, serif">This is a paragraph</p></td>
</tr>
</tbody></table>

- **sans - serif字体**

<table class="reference">
<tbody><tr>
<th width="55%" align="left">字体</th>
<th align="left">文本示例</th>
</tr>
<tr>
<td>Arial, Helvetica, sans-serif</td>
<td><h2 style="margin-top:0px;font-family: Arial, Helvetica, sans-serif">This is a heading</h2><p style="margin-bottom:0px;font-family: Arial, Helvetica, sans-serif">This is a paragraph</p></td>
</tr>
<tr>
<td>Arial Black, Gadget, sans-serif</td>
<td><h2 style="margin-top:0px;font-family: Arial Black, Gadget, sans-serif;font-weight:normal">This is a heading</h2><p style="margin-bottom:0px;font-family: Arial Black, Gadget, sans-serif">This is a paragraph</p></td>
</tr>
<tr>
<td>"Comic Sans MS", cursive, sans-serif</td>
<td><h2 style="margin-top:0px;font-family: 'Comic Sans MS', cursive, sans-serif">This is a heading</h2><p style="margin-bottom:0px;font-family: 'Comic Sans MS', cursive, sans-serif">This is a paragraph</p></td>
</tr>
<tr>
<td>Impact, Charcoal, sans-serif</td>
<td><h2 style="margin-top:0px;font-family: Impact, Charcoal, sans-serif;font-weight:normal">This is a heading</h2><p style="margin-bottom:0px;font-family: Impact, Charcoal, sans-serif">This is a paragraph</p></td>
</tr>
<tr>
<td>"Lucida Sans Unicode", "Lucida Grande", sans-serif</td>
<td><h2 style="margin-top:0px;font-family: 'Lucida Sans Unicode', 'Lucida Grande', sans-serif">This is a heading</h2><p style="margin-bottom:0px;font-family: 'Lucida Sans Unicode', 'Lucida Grande', sans-serif">This is a paragraph</p></td>
</tr>
<tr>
<td>Tahoma, Geneva, sans-serif</td>
<td><h2 style="margin-top:0px;font-family: Tahoma, Geneva, sans-serif">This is a heading</h2><p style="margin-bottom:0px;font-family: Tahoma, Geneva, sans-serif">This is a paragraph</p></td>
</tr>
<tr>
<td>"Trebuchet MS", Helvetica, sans-serif</td>
<td><h2 style="margin-top:0px;font-family: 'Trebuchet MS', Helvetica, sans-serif">This is a heading</h2><p style="margin-bottom:0px;font-family: 'Trebuchet MS', Helvetica, sans-serif">This is a paragraph</p></td>
</tr>
<tr>
<td>Verdana, Geneva, sans-serif</td>
<td><h2 style="margin-top:0px;font-family: Verdana, Geneva, sans-serif">This is a heading</h2><p style="margin-bottom:0px;font-family: Verdana, Geneva, sans-serif">This is a paragraph</p></td>
</tr>
</tbody></table>

- **Monospace 字体**
<table class="reference">
<tbody><tr>
<th width="55%" align="left">字体</th>
<th align="left">文本示例</th>
</tr>
<tr>
<td>"Courier New", Courier, monospace</td>
<td><h2 style="margin-top:0px;font-family: 'Courier New', Courier, monospace">This is a heading</h2><p style="margin-bottom:0px;font-family: 'Courier New', Courier, monospace">This is a paragraph</p></td>
</tr>
<tr>
<td>"Lucida Console", Monaco, monospace</td>
<td><h2 style="margin-top:0px;font-family: 'Lucida Console', Monaco, monospace">This is a heading</h2><p style="margin-bottom:0px;font-family: 'Lucida Console', Monaco, monospace">This is a paragraph</p></td>
</tr>
</tbody></table>


>### **用em设置字体大小**
为了避免Internet Explorer 中无法调整文本的问题，许多开发者使用 em 单位代替像素。

em的尺寸单位由W3C建议。

1em和当前字体大小相等。在浏览器中默认的文字大小是16px。

因此，1em的默认大小是16px。可以通过下面这个公式将像素转换为em：`px/16=em`

---

## 7. CSS Link

>### **链接样式**
链接的样式，可以用任何CSS属性。
特别的链接，可以有不同的样式，这取决于他们是什么状态。
这四个链接状态是：
- `a:link` - 正常，未访问过的链接
- `a:visited` - 用户已访问过的链接
- `a:hover` - 当用户鼠标放在链接上时
- `a:active` - 链接被点击的那一刻


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
a:link {color:#000000;}      /* 未访问链接*/
a:visited {color:#00FF00;}  /* 已访问链接 */
a:hover {color:#FF00FF;}  /* 鼠标移动到链接上 */
a:active {color:#0000FF;}  /* 鼠标点击时 */
</style>
</head>
<body>
<p><b><a href="/css/" target="_blank">这是一个链接</a></b></p>
<p><b>注意：</b> a:hover 必须在 a:link 和 a:visited 之后，需要严格按顺序才能看到效果。</p>
<p><b>注意：</b> a:active 必须在 a:hover 之后。</p>
</body>
</html>

>### **常见的链接样式**
根据上述链接的颜色变化的例子，看它是在什么状态。

- **文本修饰**
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
a:link {text-decoration:none;}    /* unvisited link */
a:visited {text-decoration:none;} /* visited link */
a:hover {text-decoration:underline;}   /* mouse over link */
a:active {text-decoration:overline;}  /* selected link */
</style>
</head>

<body>
<p><b><a href="/css/" target="_blank">This is a link</a></b></p>
<p><b>注意:</b> hover必须在:link和 a:visited之后定义才有效.</p>
<p><b>注意:</b>active必须在hover之后定义是有效的.</p>
</body>
</html>

- **背景颜色**

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
a:link {background-color:#B2FF99;}    /* 未访问链接 */
a:visited {background-color:#FFFF85;} /* 已访问链接 */
a:hover {background-color:#FF704D;}   /* 鼠标移动到链接上 */
a:active {background-color:#FF704D;}  /* 鼠标点击时 */
</style>
</head>

<body>
<p><b><a href="/css/" target="_blank">这是一个链接</a></b></p>
<p><b>注意:</b> hover必须在:link和 a:visited之后定义才有效.</p>
<p><b>注意:</b>active必须在hover之后定义是有效的.</p>
</body>
</html>

<p style='color:red'> 这里由于全局a标签的属性发生叠加，上面的三个实例表现一样。

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
a:link,a:visited
{
    display:block;
    font-weight:bold;
    color:#FFFFFF;
    background-color:#98bf21;
    width:120px;
    text-align:center;
    padding:4px;
    text-decoration:none;
}
a:hover,a:active
{
    background-color:#7A991A;
}
</style>
</head>
<body>
<a href="/css/" target="_blank">这是一个链接</a>
</body>
</html>
```
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
a:link,a:visited
{
	display:block;
	font-weight:bold;
	color:#FFFFFF;
	background-color:#98bf21;
	width:120px;
	text-align:center;
	padding:4px;
	text-decoration:none;
}
a:hover,a:active
{
	background-color:#7A991A;
}
</style>
</head>

<body>
<a href="/css/" target="_blank">这是一个链接</a>
</body>
</html>

---

## 8. CSS 列表样式 ( ul )

CSS列表属性作用如下：
- 设置不同的列表项标记为有序列表
- 设置不同的列表项标记为无序列表
- 设置列表项标记为图像

>### **列表**

在HTML中，有两种类型的列表：
- 无序列表 - 列表项标记用特殊图形
- 有序列表 - 列表项的标记有数字或字母

使用CSS，可以列出进一步的样式，并可用图像作列表项标记。

>### **不同的列表项标记**

`list-style-type` 属性指定列表项标记的类型：
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
ul.a {list-style-type:circle;}
ul.b {list-style-type:square;}
ol.c {list-style-type:upper-roman;}
ol.d {list-style-type:lower-alpha;}
</style>
</head>

<body>
<p>无序列表实例:</p>

<ul class="a">
  <li>Coffee</li>
  <li>Tea</li>
  <li>Coca Cola</li>
</ul>

<ul class="b">
  <li>Coffee</li>
  <li>Tea</li>
  <li>Coca Cola</li>
</ul>

<p>有序列表实例:</p>

<ol class="c">
  <li>Coffee</li>
  <li>Tea</li>
  <li>Coca Cola</li>
</ol>

<ol class="d">
  <li>Coffee</li>
  <li>Tea</li>
  <li>Coca Cola</li>
</ol>

</body>
</html>
```
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
ul.a {list-style-type:circle;}
ul.b {list-style-type:square;}
ol.c {list-style-type:upper-roman;}
ol.d {list-style-type:lower-alpha;}
</style>
</head>

<body>
<p>无序列表实例:</p>

<ul class="a">
  <li>Coffee</li>
  <li>Tea</li>
  <li>Coca Cola</li>
</ul>

<ul class="b">
  <li>Coffee</li>
  <li>Tea</li>
  <li>Coca Cola</li>
</ul>

<p>有序列表实例:</p>

<ol class="c">
  <li>Coffee</li>
  <li>Tea</li>
  <li>Coca Cola</li>
</ol>

<ol class="d">
  <li>Coffee</li>
  <li>Tea</li>
  <li>Coca Cola</li>
</ol>

</body>
</html>

>### **作为列表项标记的图像**
要指定列表项标记的图像，使用列表样式图像属性：
```css
ul{
    list-style-image: url('sqpurple.gif')
}
```
>### **浏览器兼容性解决方案**
```css
ul
{
    list-style-type: none;
    padding: 0px;
    margin: 0px;
}
ul li
{
    background-image: url(sqpurple.gif);
    background-repeat: no-repeat;
    background-position: 0px 5px; 
    padding-left: 14px; 
}
```
- ul:
    - 设置列表样式类型为没有删除列表项标记
    - 设置填充和边距0px（浏览器兼容性）

- ul中所有li：
    - 设置图像的URL，并设置它只显示一次
    - 您需要的定位图像位置
    - 用padding-left属性把文本置于列表中

>### **列表 - 简写属性**
在单个属性中可以指定所有的列表属性，这就是所谓的简写属性。

为列表使用简写属性，列表样式属性设置如下：
 ```css
ul
{
    list-style: square url("sqpurple.gif");
}
 ```

 可以按顺序设置如下属性：

 - list-style-type
 - list-style-position
 - list-style-image

 >### **所有不同的列表项标记**
 ```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
ul.a {list-style-type:circle;}
ul.b {list-style-type:disc;}
ul.c {list-style-type:square;}

ol.d {list-style-type:armenian;}
ol.e {list-style-type:cjk-ideographic;}
ol.f {list-style-type:decimal;}
ol.g {list-style-type:decimal-leading-zero;}
ol.h {list-style-type:georgian;}
ol.i {list-style-type:hebrew;}
ol.j {list-style-type:hiragana;}
ol.k {list-style-type:hiragana-iroha;}
ol.l {list-style-type:katakana;}
ol.m {list-style-type:katakana-iroha;}
ol.n {list-style-type:lower-alpha;}
ol.o {list-style-type:lower-greek;}
ol.p {list-style-type:lower-latin;}
ol.q {list-style-type:lower-roman;}
ol.r {list-style-type:upper-alpha;}
ol.s {list-style-type:upper-latin;}
ol.t {list-style-type:upper-roman;}

ol.u {list-style-type:none;}
ol.v {list-style-type:inherit;}

</style>
</head>

<body>
<ul class="a">
<li>Circle type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ul>

<ul class="b">
<li>Disc type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ul>

<ul class="c">
<li>Square type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ul>

<ol class="d">
<li>Armenian type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="e">
<li>Cjk-ideographic type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="f">
<li>Decimal type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="g">
<li>Decimal-leading-zero type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="h">
<li>Georgian type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="i">
<li>Hebrew type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="j">
<li>Hiragana type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="k">
<li>Hiragana-iroha type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="l">
<li>Katakana type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="m">
<li>Katakana-iroha type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="n">
<li>Lower-alpha type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="o">
<li>Lower-greek type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="p">
<li>Lower-latin type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="q">
<li>Lower-roman type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="r">
<li>Upper-alpha type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="s">
<li>Upper-latin type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="t">
<li>Upper-roman type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="u">
<li>None type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="v">
<li>inherit type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

</body>
</html>
 ```
 <!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
ul.a {list-style-type:circle;}
ul.b {list-style-type:disc;}
ul.c {list-style-type:square;}

ol.d {list-style-type:armenian;}
ol.e {list-style-type:cjk-ideographic;}
ol.f {list-style-type:decimal;}
ol.g {list-style-type:decimal-leading-zero;}
ol.h {list-style-type:georgian;}
ol.i {list-style-type:hebrew;}
ol.j {list-style-type:hiragana;}
ol.k {list-style-type:hiragana-iroha;}
ol.l {list-style-type:katakana;}
ol.m {list-style-type:katakana-iroha;}
ol.n {list-style-type:lower-alpha;}
ol.o {list-style-type:lower-greek;}
ol.p {list-style-type:lower-latin;}
ol.q {list-style-type:lower-roman;}
ol.r {list-style-type:upper-alpha;}
ol.s {list-style-type:upper-latin;}
ol.t {list-style-type:upper-roman;}

ol.u {list-style-type:none;}
ol.v {list-style-type:inherit;}

</style>
</head>

<body>
<ul class="a">
<li>Circle type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ul>

<ul class="b">
<li>Disc type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ul>

<ul class="c">
<li>Square type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ul>

<ol class="d">
<li>Armenian type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="e">
<li>Cjk-ideographic type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="f">
<li>Decimal type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="g">
<li>Decimal-leading-zero type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="h">
<li>Georgian type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="i">
<li>Hebrew type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="j">
<li>Hiragana type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="k">
<li>Hiragana-iroha type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="l">
<li>Katakana type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="m">
<li>Katakana-iroha type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="n">
<li>Lower-alpha type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="o">
<li>Lower-greek type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="p">
<li>Lower-latin type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="q">
<li>Lower-roman type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="r">
<li>Upper-alpha type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="s">
<li>Upper-latin type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="t">
<li>Upper-roman type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="u">
<li>None type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

<ol class="v">
<li>inherit type</li>
<li>Tea</li>
<li>Coca Cola</li>
</ol>

</body>
</html>

>### **所有的CSS列表属性**
属性 |	描述
-|-
`list-style` |	简写属性。用于把所有用于列表的属性设置于一个声明中
`list-style-image` |	将图象设置为列表项标志。
`list-style-position` |	设置列表中列表项标志的位置。
`list-style-type` |	设置列表项标志的类型。

---

- **list-style-position**

`list-style-positon` 属性指示如何相对于对象的内容绘制列表项标记。

值 |	描述
-|-
`inside` 	|列表项目标记放置在文本以内，且环绕文本根据标记对齐。
`outside` |	默认值。保持标记位于文本的左侧。列表项目标记放置在文本以外，且环绕文本不根据标记对齐。
`inherit` |	规定应该从父元素继承 list-style-position 属性的值。

```html
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style type="text/css">
ul.inside 
{
	list-style-position: inside
}

ul.outside 
{
	list-style-position: outside
}
</style>
</head>

<body>
<p>该列表的 list-style-position 的值是 "inside"：</p>
<ul class="inside">
<li>Earl Grey Tea - 一种黑颜色的茶</li>
<li>Jasmine Tea - 一种神奇的“全功能”茶</li>
<li>Honeybush Tea - 一种令人愉快的果味茶</li>
</ul>

<p>该列表的 list-style-position 的值是 "outside"：</p>
<ul class="outside">
<li>Earl Grey Tea - 一种黑颜色的茶</li>
<li>Jasmine Tea - 一种神奇的“全功能”茶</li>
<li>Honeybush Tea - 一种令人愉快的果味茶</li>
</ul>
</body>
</html>
```
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style type="text/css">
ul.inside 
{
	list-style-position: inside
}

ul.outside 
{
	list-style-position: outside
}
</style>
</head>

<body>
<p>该列表的 list-style-position 的值是 "inside"：</p>
<ul class="inside">
<li>Earl Grey Tea - 一种黑颜色的茶</li>
<li>Jasmine Tea - 一种神奇的“全功能”茶</li>
<li>Honeybush Tea - 一种令人愉快的果味茶</li>
</ul>

<p>该列表的 list-style-position 的值是 "outside"：</p>
<ul class="outside">
<li>Earl Grey Tea - 一种黑颜色的茶</li>
<li>Jasmine Tea - 一种神奇的“全功能”茶</li>
<li>Honeybush Tea - 一种令人愉快的果味茶</li>
</ul>
</body>
</html>

---

- **list-style-type**

`list-style-type` 属性设置列表项标记的类型。

值 	|描述
-|-
none |	无标记。
disc |	默认。标记是实心圆。
circle |	标记是空心圆。
square |	标记是实心方块。
decimal |	标记是数字。
decimal-leading-zero 	|0开头的数字标记。(01, 02, 03, 等。)
lower-roman |	小写罗马数字(i, ii, iii, iv, v, 等。)
upper-roman 	|大写罗马数字(I, II, III, IV, V, 等。)
lower-alpha 	|小写英文字母The marker is lower-alpha (a, b, c, d, e, 等。)
upper-alpha 	|大写英文字母The marker is upper-alpha (A, B, C, D, E, 等。)
lower-greek 	|小写希腊字母(alpha, beta, gamma, 等。)
lower-latin 	|小写拉丁字母(a, b, c, d, e, 等。)
upper-latin 	|大写拉丁字母(A, B, C, D, E, 等。)
hebrew 	|传统的希伯来编号方式
armenian 	|传统的亚美尼亚编号方式
georgian 	|传统的乔治亚编号方式(an, ban, gan, 等。)
cjk-ideographic 	|简单的表意数字
hiragana 	|标记是：a, i, u, e, o, ka, ki, 等。（日文片假名）
katakana 	|标记是：A, I, U, E, O, KA, KI, 等。（日文片假名）
hiragana-iroha 	|标记是：i, ro, ha, ni, ho, he, to, 等。（日文片假名）
katakana-iroha 	|标记是：I, RO, HA, NI, HO, HE, TO, 等。（日文片假名）

---

## 9. CSS Table（表格）
>### **表格边框**
指定CSS表格边框，使用border属性。

下面的例子指定了一个表格的th和td元素的黑色边框：

```css
table, th, td
{
    border: 1px solid black;
}
```
<p style='color:red'>在上面的例子中的表格有双边框，这是因为表和th/td元素有独立的边界。为了显示一个表的单个边框，使用border-collapse属性。

>### **折叠边框**
`border-collapse` 属性设置表格的边框是否被折叠成一个单一的边框或隔开：
```css
table
{
    border-collapse:collapse;
}
table,th, td
{
    border: 1px solid black;
}
```

>### **表格宽度和高度**
`width` 和 `height` 属性定义表格的宽度和高度。

下面的例子是设置100%的宽度，50px的th元素的高度的表格：
```css
table 
{
    width:100%;
}
th
{
    height:50px;
}
```

>### **表格文字对齐**
表格中的文本对齐和垂直对齐属性。

`text-align` 属性设置水平对齐方式 ，向左，向右，或中心。

```css
td{
    text-align:right;
}
```

垂直对齐属性设置垂直对齐，比如顶部，底部或中间：
```css
td{
    height:50px;
    vertical-align:bottom;
}
```

>### **表格填充**
如果在表的内容中控制空格之间的边距，应使用td和th元素的填充属性：
```css
td{
    padding:15px;
}
```

>### **表格颜色**
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title>
<style>
table, td, th
{
    border:1px solid green;
}
th
{
    background-color:green;
    color:white;
}
</style>
</head>

<body>
<table>
<tr>
<th>Firstname</th>
<th>Lastname</th>
<th>Savings</th>
</tr>
<tr>
<td>Peter</td>
<td>Griffin</td>
<td>$100</td>
</tr>
<tr>
<td>Lois</td>
<td>Griffin</td>
<td>$150</td>
</tr>
<tr>
<td>Joe</td>
<td>Swanson</td>
<td>$300</td>
</tr>
<tr>
<td>Cleveland</td>
<td>Brown</td>
<td>$250</td>
</tr>
</table>
</body>
</html>
```
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title>
<style>
table, td, th
{
	border:1px solid green;
}
th
{
	background-color:green;
	color:white;
}
</style>
</head>

<body>
<table>
<tr>
<th>Firstname</th>
<th>Lastname</th>
<th>Savings</th>
</tr>
<tr>
<td>Peter</td>
<td>Griffin</td>
<td>$100</td>
</tr>
<tr>
<td>Lois</td>
<td>Griffin</td>
<td>$150</td>
</tr>
<tr>
<td>Joe</td>
<td>Swanson</td>
<td>$300</td>
</tr>
<tr>
<td>Cleveland</td>
<td>Brown</td>
<td>$250</td>
</tr>
</table>
</body>
</html>

>### **制作一个个性表格**
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title>
<style>
#customers
{
	font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
	width:100%;
	border-collapse:collapse;
}
#customers td, #customers th 
{
	font-size:1em;
	border:1px solid #98bf21;
	padding:3px 7px 2px 7px;
}
#customers th 
{
	font-size:1.1em;
	text-align:left;
	padding-top:5px;
	padding-bottom:4px;
	background-color:#A7C942;
	color:#ffffff;
}
#customers tr.alt td 
{
	color:#000000;
	background-color:#EAF2D3;
}
</style>
</head>

<body>
<table id="customers">
<tr>
  <th>Company</th>
  <th>Contact</th>
  <th>Country</th>
</tr>
<tr>
<td>Alfreds Futterkiste</td>
<td>Maria Anders</td>
<td>Germany</td>
</tr>
<tr class="alt">
<td>Berglunds snabbköp</td>
<td>Christina Berglund</td>
<td>Sweden</td>
</tr>
<tr>
<td>Centro comercial Moctezuma</td>
<td>Francisco Chang</td>
<td>Mexico</td>
</tr>
<tr class="alt">
<td>Ernst Handel</td>
<td>Roland Mendel</td>
<td>Austria</td>
</tr>
<tr>
<td>Island Trading</td>
<td>Helen Bennett</td>
<td>UK</td>
</tr>
<tr class="alt">
<td>Königlich Essen</td>
<td>Philip Cramer</td>
<td>Germany</td>
</tr>
<tr>
<td>Laughing Bacchus Winecellars</td>
<td>Yoshi Tannamuri</td>
<td>Canada</td>
</tr>
<tr class="alt">
<td>Magazzini Alimentari Riuniti</td>
<td>Giovanni Rovelli</td>
<td>Italy</td>
</tr>
<tr>
<td>North/South</td>
<td>Simon Crowther</td>
<td>UK</td>
</tr>
<tr class="alt">
<td>Paris spécialités</td>
<td>Marie Bertrand</td>
<td>France</td>
</tr>
</table>
</body>
</html>
```

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title>
<style>
#customers
{
	font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
	width:100%;
	border-collapse:collapse;
}
#customers td, #customers th 
{
	font-size:1em;
	border:1px solid #98bf21;
	padding:3px 7px 2px 7px;
}
#customers th 
{
	font-size:1.1em;
	text-align:left;
	padding-top:5px;
	padding-bottom:4px;
	background-color:#A7C942;
	color:#ffffff;
}
#customers tr.alt td 
{
	color:#000000;
	background-color:#EAF2D3;
}
</style>
</head>

<body>
<table id="customers">
<tr>
  <th>Company</th>
  <th>Contact</th>
  <th>Country</th>
</tr>
<tr>
<td>Alfreds Futterkiste</td>
<td>Maria Anders</td>
<td>Germany</td>
</tr>
<tr class="alt">
<td>Berglunds snabbköp</td>
<td>Christina Berglund</td>
<td>Sweden</td>
</tr>
<tr>
<td>Centro comercial Moctezuma</td>
<td>Francisco Chang</td>
<td>Mexico</td>
</tr>
<tr class="alt">
<td>Ernst Handel</td>
<td>Roland Mendel</td>
<td>Austria</td>
</tr>
<tr>
<td>Island Trading</td>
<td>Helen Bennett</td>
<td>UK</td>
</tr>
<tr class="alt">
<td>Königlich Essen</td>
<td>Philip Cramer</td>
<td>Germany</td>
</tr>
<tr>
<td>Laughing Bacchus Winecellars</td>
<td>Yoshi Tannamuri</td>
<td>Canada</td>
</tr>
<tr class="alt">
<td>Magazzini Alimentari Riuniti</td>
<td>Giovanni Rovelli</td>
<td>Italy</td>
</tr>
<tr>
<td>North/South</td>
<td>Simon Crowther</td>
<td>UK</td>
</tr>
<tr class="alt">
<td>Paris spécialités</td>
<td>Marie Bertrand</td>
<td>France</td>
</tr>
</table>
</body>
</html>

>### **设置表格标题的位置**
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title>
<style>
caption {caption-side:bottom;}
</style>
</head>

<body>

<table border="1">
<caption>Table 1.1 Customers</caption>
<tr>
  <th>Company</th>
  <th>Contact</th>
  <th>Country</th>
</tr>
<tr>
<td>Alfreds Futterkiste</td>
<td>Maria Anders</td>
<td>Germany</td>
</tr>
<tr>
<td>Berglunds snabbköp</td>
<td>Christina Berglund</td>
<td>Sweden</td>
</tr>
<tr>
<td>Centro comercial Moctezuma</td>
<td>Francisco Chang</td>
<td>Mexico</td>
</tr>
<tr>
<td>Ernst Handel</td>
<td>Roland Mendel</td>
<td>Austria</td>
</tr>
<tr>
<td>Island Trading</td>
<td>Helen Bennett</td>
<td>UK</td>
</tr>
<tr>
<td>Magazzini Alimentari Riuniti</td>
<td>Giovanni Rovelli</td>
<td>Italy</td>
</tr>
<tr>
<td>North/South</td>
<td>Simon Crowther</td>
<td>UK</td>
</tr>
</table>

<p><b>注意:</b>如果 !DOCTYPE 指定 IE 8 支持 caption-side 属性 .</p>
</body>
</html>
```
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title>
<style>
caption {caption-side:bottom;}
h2{
    color:red;
    font-weight:bold;
}
</style>
</head>

<body>
<table border="1">
<caption>Table 1.1 Customers</caption>
<tr>
  <th>Company</th>
  <th>Contact</th>
  <th>Country</th>
</tr>
<tr>
<td>Alfreds Futterkiste</td>
<td>Maria Anders</td>
<td>Germany</td>
</tr>
<tr>
<td>Berglunds snabbköp</td>
<td>Christina Berglund</td>
<td>Sweden</td>
</tr>
<tr>
<td>Centro comercial Moctezuma</td>
<td>Francisco Chang</td>
<td>Mexico</td>
</tr>
<tr>
<td>Ernst Handel</td>
<td>Roland Mendel</td>
<td>Austria</td>
</tr>
<tr>
<td>Island Trading</td>
<td>Helen Bennett</td>
<td>UK</td>
</tr>
<tr>
<td>Magazzini Alimentari Riuniti</td>
<td>Giovanni Rovelli</td>
<td>Italy</td>
</tr>
<tr>
<td>North/South</td>
<td>Simon Crowther</td>
<td>UK</td>
</tr>
</table>

<p><b>注意:</b>如果 !DOCTYPE 指定 IE 8 支持 caption-side 属性 .</p>
</body>
</html>

---

## 10. CSS 盒子模型

>### **CSS 盒子模型（Box Model）**

所有HTML元素可以看作盒子，在CSS中，“box model”用于设计和布局使用。

CSS盒模型本质上时一个盒子，封装周围的HTML元素，它包括：边距、边框、填充和实际内容。

盒模型允许我们在其他元素和周围元素边框之间的空间放置元素。

**下面的图片说明了盒子模型：**

![盒子模型](http://www.runoob.com/images/box-model.gif)

**不同部分的说明：**
- **`Margin(外边距)`** - 清除边框外的区域，外边距是透明的。
- **`Border(边框)`** - 围绕在内边距和内容外的边框。
- **`Padding(内边距)`** - 清除内容周围的区域，内边框是透明的。
- **`Content(内容)`** - 盒子的内容，显示文本和图像。

<p style="color:red">为了正确设置元素在所有浏览器中的宽度和高度，你需要知道盒模型是如何工作的。</p>

>### **元素的宽度和高度**
<strong style="color:red">重要：当您指定一个CSS元素的宽度和高度属性时，你知识设置内容区域的宽度和高度。要知道，完全大小的元素，你还必须添加填充、边框和边距。</strong>

下面的例子中的元素的总宽度是300px：
```css
div{
    width:300px;
    border:25px solid green;
    padding:25px;
    margin:25px;
}
```
总宽度：300 + 25*2 + 25*2 + 25*2 = 450px

>### **浏览器的兼容性问题**
一旦为页面设置了恰当的 DTD，大多数浏览器都会按照上面的图示来呈现内容。然而 IE 5 和 6 的呈现却是不正确的。根据 W3C 的规范，元素内容占据的空间是由 width 属性设置的，而内容周围的 padding 和 border 值是另外计算的。不幸的是，IE5.X 和 6 在怪异模式中使用自己的非标准模型。这些浏览器的 width 属性不是内容的宽度，而是内容、内边距和边框的宽度的总和。

虽然有方法解决这个问题。但是目前最好的解决方案是回避这个问题。也就是，不要给元素添加具有指定宽度的内边距，而是尝试将内边距或外边距添加到元素的父元素和子元素。

IE8 及更早IE版本不支持设置填充的宽度和边框的宽度属性。

解决IE8及更早版本不兼容问题可以在HTML页面声明 <!DOCTYPE html>即可。

## 11. CSS 边框

>### **CSS边框属性**

CSS边框属性允许你指定一个元素边框的样式和颜色。

>### **边框样式 border-style**

`border-style`属性用来定义边框的样式。
**border-style取值：**
- <p style="border: 1px none #000000;padding:3px">none: 默认无边框</p><br>
- <p style="border: 1px dotted #000000;padding:3px">dotted: 定义一个点线边框</p><br>
- <p style="border: 1px dashed #000000;padding:3px">dashed: 定义一个虚线边框</p><br>
- <p style="border: 1px solid #000000;padding:3px">solid: 定义实线边框</p><br>
- <p style="border: 3px double #000000;padding:3px">double: 定义两个边框。 两个边框的宽度和 border-width 的值相同</p><br>
- <p style="border: 5px groove #98bf21;padding:3px">groove: 定义3D沟槽边框。效果取决于边框的颜色值</p><br>
- <p style="border: 5px ridge #98bf21;padding:3px">ridge: 定义3D脊边框。效果取决于边框的颜色值</p><br>
- <p style="border: 5px inset #98bf21;padding:3px">inset:定义一个3D的嵌入边框。效果取决于边框的颜色值</p><br>
- <p style="border: 5px outset #98bf21;padding:3px">outset: 定义一个3D突出边框。 效果取决于边框的颜色值</p><br>

>### **边框宽度 border-width**

通过 `border-width` 属性为边框指定宽度。

为边框指定宽度有两种方法：可以指定长度值，比如2px或0.1em(px、pt、cm、em...)，或者使用3个关键字之一，他们分别是thick、medium（默认值）和thin。

<p style="color:red"> CSS 没有定义 3 个关键字的具体宽度，所以一个用户可能把 thick 、medium 和 thin 分别设置为等于 5px、3px 和 2px，而另一个用户则分别设置为 3px、2px 和 1px。

>### **边框颜色 border-color**
`border-color`属性用于设置边框的颜色，您还可以设置边框的颜色为"transparent"（透明）。
<p style="color:red">border-color单独使用时不起作用的，必须得先使用border-style来设置边框样式。

>### **边框-单独设置各边**
在CSS中，可以指定不同的侧面不同的边框：
```css
p{
    border-top-style:dotted;
    border-right-style:solid;
    border-bottom-style:dotted;
    border-left-style:solid;
}
```
也可以设置一个单一属性：
```css
border-style:dotted solid;
```

`border-style`属性可以有1-4个值：
1. border-style:dotted solid double dashed; - 👆 👉 👇 👈
2. border-style:dotted solid double; - 👆 👈👉 👇
3. border-style:dotted solid; - 👆👇 👈👉
4. border-style:dotted; - 👆👇👈👉

>### **边框 - 简写属性**
可以在`border`属性中设置：
- border-width
- border-style(required)
- border-color

>### **CSS 边框属性**
属性 |	描述
-|-
`border` |	简写属性，用于把针对四个边的属性设置在一个声明。
`border-style` |	用于设置元素所有边框的样式，或者单独地为各边设置边框样式。
`border-width` |	简写属性，用于为元素的所有边框设置宽度，或者单独地为各边边框设置宽度。
`border-color` |	简写属性，设置元素的所有边框中可见部分的颜色，或为 4 个边分别设置颜色。
`border-bottom` |	简写属性，用于把下边框的所有属性设置到一个声明中。
`border-bottom-color` |	设置元素的下边框的颜色。
`border-bottom-style` 	|设置元素的下边框的样式。
`border-bottom-width` |	设置元素的下边框的宽度。
`border-left` |	简写属性，用于把左边框的所有属性设置到一个声明中。
`border-left-color` |	设置元素的左边框的颜色。
`border-left-style` |	设置元素的左边框的样式。
`border-left-width` |	设置元素的左边框的宽度。
`border-right` |	简写属性，用于把右边框的所有属性设置到一个声明中。
`border-right-color` |	设置元素的右边框的颜色。
`border-right-style`|	设置元素的右边框的样式。
`border-right-width`| 	设置元素的右边框的宽度。
`border-top` |	简写属性，用于把上边框的所有属性设置到一个声明中。
`border-top-color` |	设置元素的上边框的颜色。
`border-top-style` |	设置元素的上边框的样式。
`border-top-width` |	设置元素的上边框的宽度。
`border-radius`| 设置边框的圆角半径

## 12. CSS 轮廓（outline）

轮廓（`outline`）是绘制于元素周围的一条线，位于边框边缘的外围，可起到突出元素的作用。

轮廓（`outline`）属性指定元素轮廓的样式、颜色和宽度。
>### **轮廓实例**
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
#outline{
	border:1px solid red;
	outline:green dotted thick;
}
</style>
</head>

<body>
<p id="outline"><b>注意:</b> 如果只有一个 !DOCTYP E指定 IE8 支持 outline 属性。</p>
<style>
p.dotted {outline-style:dotted;}
p.dashed {outline-style:dashed;}
p.solid {outline-style:solid;}
p.double {outline-style:double;}
p.groove {outline-style:groove;}
p.ridge {outline-style:ridge;}
p.inset {outline-style:inset;}
p.outset {outline-style:outset;}
</style>
</head>
<body>
<p class="dotted">点线轮廓</p>
<p class="dashed">虚线轮廓</p>
<p class="solid">实线轮廓</p>
<p class="double">双线轮廓</p>
<p class="groove">凹槽轮廓</p>
<p class="ridge">垄状轮廓</p>
<p class="inset">嵌入轮廓</p>
<p class="outset">外凸轮廓</p>
<p><b>注意:</b> 如果只有一个 !DOCTYPE 指定 IE 8 支持 outline 属性。</p>
</body>
</html>
```
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
#outline{
	border:1px solid red;
	outline:green dotted thick;
}
</style>
</head>

<body>
<p id="outline"><b>注意:</b> 如果只有一个 !DOCTYP E指定 IE8 支持 outline 属性。</p>
<style>
p.dotted {outline-style:dotted;}
p.dashed {outline-style:dashed;}
p.solid {outline-style:solid;}
p.double {outline-style:double;}
p.groove {outline-style:groove;}
p.ridge {outline-style:ridge;}
p.inset {outline-style:inset;}
p.outset {outline-style:outset;}
</style>
</head>
<body>
<p class="dotted">点线轮廓</p>
<p class="dashed">虚线轮廓</p>
<p class="solid">实线轮廓</p>
<p class="double">双线轮廓</p>
<p class="groove">凹槽轮廓</p>
<p class="ridge">垄状轮廓</p>
<p class="inset">嵌入轮廓</p>
<p class="outset">外凸轮廓</p>
<p><b>注意:</b> 如果只有一个 !DOCTYPE 指定 IE 8 支持 outline 属性。</p>
</body>
</html>

>### **CSS 轮廓（outline）**
轮廓是绘制于元素周围的一条线，位于边框边缘的外围，可起到突出元素的作用。
![轮廓](http://www.runoob.com/images/box_outline.gif)

>### **所有CSS 轮廓属性**

属性 |	说明  	|CSS
-|-|-
`outline` |	在一个声明中设置所有的轮廓属性 |	2
`outline-color` |	设置轮廓的颜色 	|2
`outline-style` |	设置轮廓的样式 	|2
`outline-width`| 	设置轮廓的宽度 	|2

- `outline` 属性

outline简写属性在一个声明中设置所有的轮廓属性。

可以设置的属性分别是(按顺序):`outline-color`,`outline-style`,`outline-width`.

值 	|描述
-|-
`outline-color` |	规定边框的颜色。参阅：outline-color 中可能的值。
`outline-style` |	规定边框的样式。参阅：outline-style 中可能的值。
`outline-width` |	规定边框的宽度。参阅：outline-width 中可能的值。
`inherit` |	规定应该从父元素继承 outline 属性的设置。

- `outline-color` 属性

<b style="color:red">注意:outline是围绕元素.它是围绕元素的边距,但是,它来自不同的边框属性.outline不是元素尺寸的一部分,因此元素的宽度和高度属性不包含轮廓的宽度.</b>

值 |	描述
-|-
`color` |	指定轮廓颜色。在 CSS颜色值寻找颜色值的完整列表。
`invert`	|默认。执行颜色反转（逆向的颜色）。可使轮廓在不同的背景颜色中都是可见。
`inherit` |	规定应该从父元素继承轮廓颜色的设置。

- `outline-style` 属性

值 |	描述
-|-
none |	默认。定义无轮廓。
dotted |	定义点状的轮廓。
dashed 	|定义虚线轮廓。
solid |	定义实线轮廓。
double |	定义双线轮廓。双线的宽度等同于 outline-width 的值。
groove |	定义 3D 凹槽轮廓。此效果取决于 outline-color 值。
ridge |	定义 3D 凸槽轮廓。此效果取决于 outline-color 值。
inset |	定义 3D 凹边轮廓。此效果取决于 outline-color 值。
outset |	定义 3D 凸边轮廓。此效果取决于 outline-color 值。
inherit |	规定应该从父元素继承轮廓样式的设置。

- `outline-width` 属性

值 	|描述
-|-
thin 	|规定细轮廓。
medium 	|默认。规定中等的轮廓。
thick 	|规定粗的轮廓。
length 	|允许您规定轮廓粗细的值。
inherit 	|规定应该从父元素继承轮廓宽度的设置。

---

## 13. CSS margin(外边距)
`CSS margin 属性定义元素周围的空间`

>### **margin**

margin清除周围的元素区域,margin没有背景颜色,是完全透明的.

margin可以单独改变元素的上,下,左,右边距,也可以一次改变所有的属性.
![margin](http://www.runoob.com/wp-content/uploads/2013/08/VlwVi.png)

>### **可能的值**

值| 	说明
-|-
auto |	设置浏览器边距。这样做的结果会依赖于浏览器
length |	定义一个固定的margin（使用像素，pt，em等）
% |	定义一个使用百分比的边距
<b style="color:red">margin可以使用负值,重叠的内容.</b>

>### **margin - 单边外边距属性**
```css
margin-top:100px;
margin-bottom:100px;
margin-right:50px;
margin-left:50px;
```

>### **margin - 简写属性**

>### **更多实例**

---

## 14. CSS padding
`padding定义元素边框与元素内容之间的空间`

---

## 15. CSS 分组 和 嵌套 选择器

>### **分组选择器**
在样式表中有很多具有相同样式的元素.

为了尽量减少代码,你可以使用分组选择器.

每个选择器用逗号分隔:
```css
h1,h2,p{
    color:green;
}
```

>### **嵌套选择器**
它可能适用于选择器内部的选择器的样式.

在下面的例子设置了三个样式:
```css
p{
    color:blue;
    text-align:center;
}
.marked{
    background-color:red;
}
.marked p{
    color:white;
}
p.marked{
    text-decoration:underline;
}
```
- `p{ }` - 为所有p元素指定一个样式.
- `.marked{ }` - 为所有 `class="marked"`的元素指定一个样式.
- `.marked p{ }` - 为所有`class="marked"`元素内的p元素指定一个样式.
- `p.marked{ }` - 为所有`class="marked"`的p元素指定一个元素.

---

## 16. CSS 尺寸(Dimension)
**`CSS dimension 属性允许你控制元素的高度和宽度,同样,它允许你增加行间距`**

>### **更多实例**


>### **所有CSS 尺寸(Dimension)属性**
属性 |	描述
-|-
`height` 	|设置元素的高度。
`line-height` |	设置行高。
`max-height` 	|设置元素的最大高度。
`max-width` 	|设置元素的最大宽度。
`min-height` 	|设置元素的最小高度。
`min-width` |	设置元素的最小宽度。
`width` |	设置元素的宽度。
<style>
    b{
        color:red
    }
</style>
<b>这个html的效果很奇怪,依赖于对html 和 body 的height:100% 才能产生下图big的伸缩效果.
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
html {height:100%;}
body {height:100%;}
img.normal {height:auto;}
img.big {height:40%;}
img.small {height:10%;}
</style>
</head>
<body>
<img class="normal" src="http://www.runoob.com/try/demo_source/logocss.gif" width="95" height="84" /><br>
<img class="big" src="http://www.runoob.com/try/demo_source/logocss.gif" width="95" height="84" /><br>
<img class="small" src="http://www.runoob.com/try/demo_source/logocss.gif" width="95" height="84" />
</body>
</html>
```

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<title>菜鸟教程(runoob.com)</title> 
<style>
html {height:100%;}
body {height:100%;}
img.normal {height:auto;}
img.big {height:40%;}
img.small {height:10%;}
</style>
</head>
<body>
<img class="normal" src="http://www.runoob.com/try/demo_source/logocss.gif" width="95" height="84" /><br>
<img class="big" src="http://www.runoob.com/try/demo_source/logocss.gif" width="95" height="84" /><br>
<img class="small" src="http://www.runoob.com/try/demo_source/logocss.gif" width="95" height="84" />
</body>
</html>