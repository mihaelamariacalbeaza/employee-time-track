
from datetime import datetime

dt_string1 = "12/11/2018 09:10:32"
dt_string2 = "12/11/2018 09:15:32"

# Considering date is in dd/mm/yyyy format
dt_object1 = datetime.strptime(dt_string1, "%d/%m/%Y %H:%M:%S")
dt_object2 = datetime.strptime(dt_string2, "%d/%m/%Y %H:%M:%S")

if(dt_string1<dt_string2):
    print("MERGEEEE")
else:
    print("MA CAC IN EL")