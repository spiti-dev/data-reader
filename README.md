# Data Reader - Simple way to read from and write to XLSX and CSV files

## Contents

*  [Key Features](#kf)
*  [Usages](#use)
*  [Limitations](#lims)
*  [Examples](#ex)
*  [Integration to `TESTNG`](#testng)

<a name="kf"></a>
## Key Features

- Reads `.xlsx` and `.csv` files
- Creates `.xlsx` and `.csv` files
- Returns data as a an array of `<Map<String, String>` where key is column name and value is cell value
- Filters data for a specific value from a given column, before returning
- Add header and rows from `ArrayList<String>`

<a name="use"></a>
## Usages

- Integrate into `TESTNG` and other automation frameworks
- Use in `@DataProvider` methods to read data and stream to `@Test` methods as `Object[]`
- Create test report files and add results after every test

<a name="lims"></a>
## Limitations

- Creates and adds data to **_XLSX_ FILES ONLY**
- Defaults to read files from `src/test/resources/` directory
- Empty row is considered as Enf of File

<a name="ex"></a>
## Examples

#### Read _XLSX_ files

```
Object[] data = new Excel("DataFile", "Sheet1").getData();
```
  - Reads data from sheet `Sheet1` from file `src/test/resources/DataFile.xlsx`
  - Streams entire sheet as `Object[]`
  - Each row is one object of `Map<String, String>`
  - **Key** is column name and **Value** is cell value from the row

```
Object[] data = new Excel("DataFile", "Sheet1").getData("testType", "Regression");
```
  - Accepts column name and cell value as parameters to filter data
  - Filters and returns data that has cell value `Regression` on column `testType`

#### Read _CSV_ files

```
Object[] data = new CSV("DataFile").getData();
```
  - Reads data from file `src/test/resources/DataFile.csv`
  - Streams entire sheet as `Object[]`
  - Each row is one object of `Map<String, String>`
  - **Key** is column name and **Value** is cell value from the row

```
Object[] data = new CSV("DataFile").getData("testType", "Regression");
```
  - Accepts column name and cell value as parameters to filter data
  - Filters and returns data that has cell value `Regression` on column `testType`

#### Create and write to _XLSX_ files

```
String fileInPath = new Excel().createFile("thisFile");
```
  - Creates `src/test/resources/results/thisFile_<timestamp>.xlsx`
  - Returns `src/test/resources/results/thisFile_<timestamp>.xlsx`
  - timestamp is in `yyyyMMddHHmm` format

```
new Excel().writeHeader(new Excel().createFile("thisFile"),"Sheet1", headerList);
```
  - Creates `Sheet1` on file `src/test/resources/results/thisFile_<timestamp>.xlsx`
  - `headerList` is List<String>. First row is created with strings as in the order from the list
  
```
new Excel().writeRow(new Excel().createFile("thisFile"),"Sheet1", rowList);
```
  - Reads `Sheet1` on file `src/test/resources/results/thisFile_<timestamp>.xlsx`
  - `rowList` is an `ArrayList`
  - Adds data from `rowList` in the row after the last row in file

<a name="testng"></a>
## Integration to `TESTNG` 

#### Read from _XLSX_ files

```
@DataProvider
public Object[] dataFromExcel() {
  return new Excel("DataFile", "Sheet1").getData("testType", "Regression");
}

@Test (dataProvider = "dataFromExcel")
public void readFromExce(Map<String, String> testdata) {
  // Data from excel is available here for test
}
```

#### Read from _CSV_ files

```
@DataProvider
public Object[] dataFromCsv() {
  return new CSV("DataFile").getData("testType", "Regression");
}

@Test (dataProvider = "dataFromCsv")
public void readFromCsv(Map<String, String> testdata) {
  // Data from excel is available here for test
}
```
