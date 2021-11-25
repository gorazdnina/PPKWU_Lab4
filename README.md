# POST - (/stringAnalysis)
Port: 8091

Uses second application on port: 8090

## Request Body - structure
{ String string, String expression, String firstFormat, String outputFormat }

# POST - (/analysisFormat)
## Request Body - structure
{ String inputFormat, String outputFormat, byte[] stringStatistics}

## Response Body
### TXT
```
    Uppercase letters: %d,
    Lowercase letters: %d,
    Digits: %d,
    Special signs: %d,
    Number of occurrences: %d
```
### JSON
```
    {
    "Uppercase letters": %d,
    "Lowercase letters": %d,
    "Digits": %d,
    "Special signs": %d,
    "Number of occurrences": %d
    }
```
### XML
```
    <countResponse>
    <Uppercase_letters>%d</Uppercase_letters>,
    <Lowercase_letters>%d</Lowercase_letters>,
    <Digits>%d</Digits>,
    <Special_signs>%d</Special_signs>,
    <Number_of_occurrences>%d</Number_of_occurrences>
    </countResponse>
```
### CSV
```
Uppercase_Letters,Lowercase_Letters, Digits, Special_Signs, Number_Of_Occurrences
%d,%d,%d,%d,%d
```