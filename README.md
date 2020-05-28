# NotePad期中作业
## 1.实验内容
1.为增加时间  
2.增加搜索功能  
## 2.实验环境
Android Studio 3.5
## 3.实验步骤
### 3.1增加时间
#### 3.1.1修改noteslist.xml文件
```
 <TextView
        android:id="@android:id/text1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:gravity="center_vertical"
        android:paddingLeft="5dip"
        android:singleLine="true"
        android:textAppearance="?android:attr/textAppearanceLarge"
        tools:layout_conversion_absoluteHeight="64dp"
        tools:layout_conversion_absoluteWidth="411dp"
        tools:layout_editor_absoluteX="0dp"
        tools:layout_editor_absoluteY="0dp" />

    <TextView
        android:id="@android:id/text2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        >
```
### 3.1.2 修改noteList类增加投影的内容，以及修改：
```
    private static final String[] PROJECTION = new String[] {
            NotePad.Notes._ID, // 0
            NotePad.Notes.COLUMN_NAME_TITLE, // 1
            NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE,
    };
```
修改dataColumns和Viewid
```
// The names of the cursor columns to display in the view, initialized to the title column
        String[] dataColumns = { NotePad.Notes.COLUMN_NAME_TITLE ,NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE} ;

        // The view IDs that will display the cursor columns, initialized to the TextView in
        // noteslist_item.xml
        int[] viewIDs = { android.R.id.text1 ,android.R.id.text2};
```
### 3.1.3 转换时间
```
values.put(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE, android.text.format.DateFormat.format("yyyyMMMdd,E",System.currentTimeMillis()).toString());
```
这样就ok啦~~  
![截图](https://raw.githubusercontent.com/jiangyi666/test/master/%E5%9B%BE1.png)  
## 3.2增加搜索功能
其实比较简单（。。。）像sql。。。
### 3.2.在1list_options_menu.xml中添加一个item
```
<item android:id="@+id/menu_query"
        android:title="@string/menu_query"
        android:alphabeticShortcut='q' />
```
### 3.2.3 建立一个searchResult类用来搜索：
```
   Cursor cursor = managedQuery(
                getIntent().getData(),            // Use the default content URI for the provider.
                PROJECTION,                       // Return the note ID and title for each note.
                NotePad.Notes.COLUMN_NAME_TITLE+" like ?",                             // No where clause, return all records.
                new String[]{mKeywords},                             // No where clause, therefore no where column values.
                NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.
        );
 ```
 ### 3.2.4 创建了SearchDialog类用于实现在查询弹窗
代码比较多，详细见项目~~~~
### 3.2.5 在NotesList中菜单选项里面进行监听调用
```
  case R.id.search_item:
            MyDialogFragment dialog=new MyDialogFragment();
            dialog.show(getFragmentManager(),null);
 ```
 这样就ok啦~~  
![截图](https://raw.githubusercontent.com/jiangyi666/test/master/%E5%9B%BE2.png)  
![截图](https://raw.githubusercontent.com/jiangyi666/test/master/%E5%9B%BE3.png)  
## 4.0总结反馈
其实我的安卓这一块还是比较差，水平有待提升，后续还要继续努力  
虽然这个期中作业可能都是一些比较简单的功能，但是，我还是写了很久~~
