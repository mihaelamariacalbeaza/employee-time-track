from tkinter import *

import requests
from datetime import datetime

# SUCCESS

SUCCESS_CREATED_TIME_TRACK = "\nSUCCESSFULLY CREATED TIME TRACK"
SUCCESS_UPDATED_TIME_TRACK = "\nSUCCESSFULLY UPDATED TIME TRACK"

# ERRORS

INVALID_EMPLOYEE_ID_ERROR = "\nINVALID EMPLOYEE ID"
INVALID_TIME_TRACK_ID_ERROR = "\nINVALID TIME TRACK ID"
INVALID_CHECK_OUT_TIME_ERROR = "\nINVALID PUNCH OUT: Incorrect data format, should be DD-MM-YYYY HH:mm"
INVALID_CHECK_IN_TIME_ERROR = "\nINVALID PUNCH IN: Incorrect data format, should be DD-MM-YYYY HH:mm"

# URL
CREATE_TIME_TRACK_URL = "http://localhost:8082/timetrack/createTimeTracking/"
GET_TIME_TRACK_BY_EMPLOYEE_ID_URL = "http://localhost:8082/timetrack/getAnEmployeeTimeTrackByEmployeeId/"
UPDATE_TIME_TRACK_URL = "http://localhost:8082/timeTrack/updateEmployeeTimeTrack/"


# METHODS
# time track method

def createTimeTrack():
    if isEmployeeIdValid(employeeIdEntry.get()):
        if isPunchInValid(punchInEntry.get()):
            if isPunchOutValid(punchOutEntry.get()):
                if punchInEntry.get() < punchOutEntry.get():
                    timeTrack = {
                        "idEmployee": employeeIdEntry.get(),
                        "punchIn": punchInEntry.get(),
                        "punchOut": punchOutEntry.get()
                    }
                    response = requests.post(CREATE_TIME_TRACK_URL, json=timeTrack)
                else:
                    printToErrorTextArea("PUCH IN CAN\'T BE A HIGHER VALUE THAN PUNCH OUT ")
                if response.status_code == 200:
                        clearOutput()
                        printToOutputText(SUCCESS_CREATED_TIME_TRACK)
                else:
                        printToErrorTextArea("Error " + str(response.status_code))


def getTimeTrackByEmployeeID():
    if isEmployeeIdValid(employeeIdEntry.get()):
        response = requests.get(GET_TIME_TRACK_BY_EMPLOYEE_ID_URL + employeeIdEntry.get())
        if response.status_code == 200:
            clearOutput()
            printToOutputText(response.json())
        else:
            printToErrorTextArea("Error " + str(response.status_code))
    else:
        printToErrorTextArea(INVALID_EMPLOYEE_ID_ERROR)


def printToOutputText(text):
    outputTextArea.config(state=NORMAL)
    outputTextArea.delete('1.0', END)
    outputTextArea.insert(END, text)
    outputTextArea.config(state=DISABLED)


def printToErrorTextArea(text):
    errorTextArea.config(state=NORMAL)
    errorTextArea.delete('1.0', END)
    errorTextArea.insert(END, text)
    errorTextArea.config(state=DISABLED)


def outputTextArea(text):
    errorTextArea.config(state=NORMAL)
    errorTextArea.delete('1.0', END)
    errorTextArea.insert(END, text)
    errorTextArea.config(state=DISABLED)


#       DATA VALIDATION
def isTimeTrackIdValid(tt_id):
    if len(tt_id) == 0:
        return False
        printToErrorTextArea(INVALID_TIME_TRACK_ID_ERROR)
    else:
        if tt_id.isnumeric():
            int(tt_id)
            return True
        else:
            printToErrorTextArea(INVALID_TIME_TRACK_ID_ERROR)
            return False


def isEmployeeIdValid(emplID):
    if len(emplID) == 0:
        return printToErrorTextArea(INVALID_EMPLOYEE_ID_ERROR)
    elif emplID.isnumeric():
        return emplID
    else:
        printToErrorTextArea(INVALID_TIME_TRACK_ID_ERROR)
        return False


def isPunchInValid(punchin):
    try:
        if len(punchin) != 0:
            punchinObj = datetime.strptime(punchin, "%d/%m/%Y %H:%M")
            return punchinObj
        else:
            printToErrorTextArea(INVALID_CHECK_OUT_TIME_ERROR)
    except ValueError:
        # raise ValueError("Incorrect data format, should be YYYY-MM-DD HH:mm")
        raise ValueError(printToErrorTextArea(INVALID_CHECK_IN_TIME_ERROR))


def isPunchOutValid(punchout):
    try:
        if len(punchout) != 0:
            punchoutObj = datetime.strptime(punchout, "%d/%m/%Y %H:%M")
            return punchoutObj
        else:
            printToErrorTextArea(INVALID_CHECK_OUT_TIME_ERROR)
    except ValueError:
        raise ValueError(printToErrorTextArea(INVALID_CHECK_OUT_TIME_ERROR))




# CLEAR INPUT AND OUTPUT
def clearInputs():
    timeTrackEntry.delete(0, END)
    employeeIdEntry.delete(0, END)
    punchInEntry.delete(0, END)
    punchOutEntry.delete(0, END)


def clearOutput():
    enable_text_areas()
    outputTextArea.delete('1.0', END)
    errorTextArea.delete('1.0', END)
    # result_text_area.delete('1.0', END)
    disable_text_areas()


def disable_text_areas():
    outputTextArea.config(state=DISABLED)
    #    result_text_area.config(state=DISABLED)
    errorTextArea.config(state=DISABLED)


def enable_text_areas():
    outputTextArea.config(state=NORMAL)
    errorTextArea.config(state=NORMAL)
    # result_text_area.config(state=NORMAL)


# ----------------------------------------------- DESIGN

root = Tk()
root.geometry("800x642")
root.title('Add your working hours')

# create a label (text)
# timeTrackId = Label(root, text="Time Track ID")
timeTrackLabel = Label(root, text="Time track Id", padx=50, pady=15, fg="#395144", bg="#EAE7B1", bd=0,
                       relief=SUNKEN)
timeTrackLabel.grid(row=0, column=0)  # shoving it into the screen

employeeIdLabel = Label(root, text="Employee Id", padx=50, pady=15, fg="#395144", bg="#EAE7B1", bd=0, relief=SUNKEN)
employeeIdLabel.grid(row=1, column=0)  # shoving it into the screen

punchInLabel = Label(root, text="Punch In", padx=59, pady=15, fg="#395144", bg="#EAE7B1", bd=0, relief=SUNKEN)
punchInLabel.grid(row=2, column=0)

punchOutLabel = Label(root, text="Punch Out", padx=55, pady=12, fg="#395144", bg="#EAE7B1", bd=0, relief=SUNKEN)
punchOutLabel.grid(row=3, column=0)

# create a button
sendTTButton = Button(root, text="Send time tract", padx=50, pady=15, fg="#395144", bg="#A6BB8D",
                      command=createTimeTrack)
sendTTButton.place(
    x=643.0,
    y=206.0,
    width=137.0,
    height=30.0
)

getMyTTButton = Button(root, text="Get my time track", padx=50, pady=15, fg="#395144", bg="#A6BB8D",
                       command=getTimeTrackByEmployeeID)
getMyTTButton.place(
    x=415.0,
    y=602.0,
    width=137.0,
    height=30.0
)

# to do clear fields button

exitButton = Button(root, text="EXIT", padx=50, pady=15, fg="#483838", bg="#61876E", command=root.quit)
exitButton.place(
    x=643.0,
    y=602.0,
    width=137.0,
    height=30.0
)

# create input boxes and shoving it in
timeTrackEntry = Entry(root, width=20, fg="#395144", bg="#F0EBCE")
timeTrackEntry.grid(row=0, column=1)

employeeIdEntry = Entry(root, width=20, fg="#395144", bg="#F0EBCE")
employeeIdEntry.grid(row=1, column=1)

punchInEntry = Entry(root, width=20, fg="#395144", bg="#F0EBCE")
punchInEntry.grid(row=2, column=1)

punchOutEntry = Entry(root, width=20, fg="#395144", bg="#F0EBCE")
punchOutEntry.grid(row=3, column=1)

labelErrorTextArea = Label(text="ERROR")
labelErrorTextArea.place(x=641.0, y=300.0)

errorTextArea = Text(bd=0, bg="#F0EBCE", fg="#395144", highlightthickness=0)
errorTextArea.place(
    x=580.0,
    y=330.0,
    width=200.0,
    height=35.0
)

labelOutputTextArea = Label(text="Output")
labelOutputTextArea.place(x=8.0,
                          y=290.0)

outputTextArea = Text(bd=0, bg="#F0EBCE", fg="#395144", highlightthickness=0)
outputTextArea.place(
    x=10.0,
    y=338.0,
    width=390.0,
    height=292.0
)

root.resizable(False, False)
root.mainloop()

# add status label
