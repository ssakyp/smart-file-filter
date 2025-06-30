# Smart File Filter

A command-line Java utility for filtering the content of input files based on data types.

The program reads multiple text files containing mixed data types (integers, floating-point numbers, and strings), and writes them into separate output files by type.

---

## ✅ Features

- Detects and separates:
  - Integers
  - Floating-point numbers (including scientific notation)
  - Strings
- Supports multiple input files
- CLI options:
  - `-o` — specify output directory
  - `-p` — prefix for output filenames
  - `-a` — append mode (instead of overwrite)
  - `-s` — show short statistics (counts only)
  - `-f` — show full statistics (min, max, average, etc.)
- Skips creating output files if no data of that type is found
- Resilient to errors: continues processing even if some files are missing or contain malformed lines
- Console output includes useful information and statistics

---

## 🚀 Getting Started

### 📦 Prerequisites

- Java 17 or later
- Maven 3.6 or later

---

### 🔧 Build the Project

```bash
mvn clean package
```

This will generate the `.jar` file in the `target/` directory.

---

### ▶️ Run the Program

```bash
java -jar target/smart-file-filter-1.0-SNAPSHOT.jar -s -p sample- in1.txt in2.txt
```

Or with full options:

```bash
java -jar target/smart-file-filter-1.0-SNAPSHOT.jar -s -a -o ./output -p result_ in1.txt in2.txt
```

---

## 🛠 Command-Line Options

| Option     | Description                                       |
|------------|---------------------------------------------------|
| `-o`       | Output directory (default is current directory)   |
| `-p`       | Prefix for output filenames                       |
| `-a`       | Append mode (adds to files instead of overwriting)|
| `-s`       | Show short statistics (counts only)               |
| `-f`       | Show full statistics                              |
| `-h`       | Show help message                                 |

> If neither `-s` nor `-f` is specified, statistics are not printed.

---

## 📂 Input Format

- Files must contain one value per line.
- Data types can be mixed.
- Supported types:
  - Integers: e.g., `45`, `100500`
  - Floats: e.g., `3.1415`, `-0.001`, `1.528535047E-25`
  - Strings: anything else

---

## 📊 Sample Statistics Output

```text
📊 Full statistics:
Integers: 3 | Min: 45 | Max: 100500 | Sum: 100590 | Avg: 33530.00
Floats: 3 | Min: -0.00100 | Max: 3.14150 | Sum: 3.14050 | Avg: 1.04700
Strings: 4 | Shortest: 4 chars | Longest: 32 chars
```

---

## 📤 Output Files

Generated only if corresponding data exists:

- `sample-integers.txt`
- `sample-floats.txt`
- `sample-strings.txt`

> Example output with prefix `sample-` and default output directory:

#### 📄 `sample-integers.txt`
```
45
100500
1234567890123456789
```

#### 📄 `sample-floats.txt`
```
3.1415
-0.001
1.528535047E-25
```

#### 📄 `sample-strings.txt`
```
Lorem ipsum
тестовое задание
Long
```

---

## 📚 Example Input Files

### `in1.txt`
```
Lorem ipsum
45
3.1415
-0.001
тестовое задание
100500
```

### `in2.txt`
```
Нормальная форма числа с плавающей запятой
1.528535047E-25
Long
1234567890123456789
```

---

## 🧪 Example Command and Output

```bash
java -jar target/smart-file-filter-1.0-SNAPSHOT.jar -f -p sample- in1.txt in2.txt
```

```text
=== Smart File Filter ===
Short stats: false
Full stats: true
Append mode: false
Output dir: .
Prefix: sample-
Input files:
- in1.txt
- in2.txt
📖 Reading file: in1.txt
📖 Reading file: in2.txt
Wrote to ./sample-integers.txt
Wrote to ./sample-floats.txt
Wrote to ./sample-strings.txt

📊 Full statistics:
Integers: 3 | Min: 45 | Max: 1234567890123456789 | Sum: ... | Avg: ...
Floats: 3 | Min: -0.00100 | Max: 3.14150 | Sum: ... | Avg: ...
Strings: 4 | Shortest: 4 chars | Longest: 32 chars
```

---

## 🧱 Technologies Used

- Java 17
- [Maven](https://maven.apache.org/) (build system)
- [Apache Commons CLI 1.5.0](https://commons.apache.org/proper/commons-cli/) (argument parsing)

---

## 👨‍💻 Author

**Sultan Sakyp**  
🔗 [GitHub Profile](https://github.com/ssakyp)

---