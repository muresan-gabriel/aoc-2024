def read_file_to_string(file_path)
  File.read(file_path)
end

def calculate_result(text)
  pattern = /mul\((\d+),(\d+)\)/

  matches = text.scan(pattern)

  total = 0

  matches.each do |first_number, second_number|
    total += first_number.to_i * second_number.to_i
  end

  total
end

def calculate_result_of_enabled(text)
  do_pattern = /do\(\)/
  dont_pattern = /don't\(\)/
  mul_pattern = /mul\((\d+),(\d+)\)/
  
  tokens = text.scan(/do\(\)|don't\(\)|mul\(\d+,\d+\)/)
  
  total = 0
  execute = true
  
  tokens.each do |token|
    if token == "do()"
      execute = true
    elsif token == "don't()"
      execute = false
    elsif token.match?(mul_pattern) && execute
      numbers = token.match(mul_pattern).captures.map(&:to_i)
      total += numbers[0] * numbers[1]
    end
  end

  total
end