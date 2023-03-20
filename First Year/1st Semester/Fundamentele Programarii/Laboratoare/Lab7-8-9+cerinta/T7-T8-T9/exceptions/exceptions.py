class LibraryException(Exception):
    pass


class ValidationException(LibraryException):
    def __init__(self, msgs):
        """
        :param msgs: lista de mesaje de eroare
        :type msgs: msgs
        """
        self.__err_msgs = msgs

    def GetMessages(self):
        return self.__err_msgs

    def __str__(self):
        return 'Validation Exception: ' + str(self.__err_msgs)


class RepositoryException(LibraryException):
    def __init__(self, msg):
        self.__msg = msg

    def getMessage(self):
        return self.__msg

    def __str__(self):
        return 'Repository Exception: ' + self.__msg


class DuplicateIDException(RepositoryException):
    def __init__(self):
        RepositoryException.__init__(self, "ID duplicat.")


class RentAlreadyAssignedException(RepositoryException):
    def __init__(self):
        RepositoryException.__init__(self, "Rent Item existent pentru cartea si clientul dat.")


class BookNotFoundException(RepositoryException):
    def __init__(self):
        RepositoryException.__init__(self, "Cartea nu a fost gasita")


class ClientNotFoundException(RepositoryException):
    def __init__(self):
        RepositoryException.__init__(self, "Clientul nu a fost gasit")


class RentItemNotFoundException(RepositoryException):
    def __init__(self):
        RepositoryException.__init__(self, "Acest Rent Item nu a fost gasit. ")


class RentItemAlreadyReturned(RepositoryException):
    def __init__(self):
        RepositoryException.__init__(self, "Acest Rent Item a fost returnat deja. ")


class CorruptedFileException(LibraryException):
    def __init__(self):
        pass