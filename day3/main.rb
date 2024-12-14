require_relative 'utils'

begin
  content = read_file_to_string("input")
  total = calculate_result(content)
  total_enabled = calculate_result_of_enabled(content)
  puts "Total: #{total}"
  puts "Total enabled: #{total_enabled}"
end
