
if __name__ == "__main__":
    
    print "To Find The Correlated Candidates to Hillary Clinton, Press 1"
    print
    print "To Find The Correlated Candidates to Bernie Sanders, Press 2"
    print
    choice = input("Enter your choice  ")
    print
    if choice == 1:
        execfile('Correlation_HC.py')
    elif choice == 2:
        execfile('Correlation_BS.py')
    