def getReports(file_content):
  reports = []  

  for line in file_content:
    report = [int(num) for num in line.split()]
    reports.append(report)
  
  return reports
        
def safe(report):
  is_increasing = all(report[i] <= report[i+1] for i in range(len(report) - 1))
  is_decreasing = all(report[i] >= report[i+1] for i in range(len(report) - 1))

  if not (is_increasing or is_decreasing):
    return False

  for i in range(len(report) - 1):
    difference = abs(report[i] - report[i+1])

    if difference > 3 or difference < 1:
      return False

  return True

def safeWithDampener(report):
  if safe(report):
    return True

  for i in range(len(report)):
    modified_report = report[:i] + report[i+1:] 
    if safe(modified_report):
      return True

  return False