from utils import getReports, safe, safeWithDampener

def main():
  input_file = open("input", "r")

  reports = getReports(input_file)

  input_file.close()

  safe_reports = 0
  safe_reports_with_dampener = 0

  for report in reports:
    if (safe(report)):
      safe_reports += 1
  
  for report in reports:
    if (safeWithDampener(report)):
      safe_reports_with_dampener += 1

  print(safe_reports)
  print(safe_reports_with_dampener)

if __name__ == "__main__":
    main()

