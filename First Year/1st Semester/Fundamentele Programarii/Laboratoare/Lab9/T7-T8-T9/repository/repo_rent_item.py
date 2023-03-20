from domain.entities import Client, Book, RentItem
from exceptions.exceptions import RentAlreadyAssignedException, RentItemNotFoundException, RentItemAlreadyReturned


class RentItemRepoMemory:
    def __init__(self):
        self.__items = []

    def finditem(self, rent_item):
        """
        Cauta un rent item in lista
        :param rent_item: rent item cautat
        :type rent_item: RentItem
        :return: RentItem daca exista in lista, None altfel
        :rtype: RentItem
        """
        for item in self.__items:
            if item == rent_item:
                return item
        return None

    def store(self, rent_item):
        """
        Adauga un rent item
        :param rent_item: rent item de adaugat
        :type rent_item: RentItem
        :return:-; se adauga RentItem in lista
        :raises: RentAlreadyAssignedException daca exista deja item pentru carte, biblioteca data
        """
        s = self.finditem(rent_item)
        if s is not None:
            raise RentAlreadyAssignedException()

        self.__items.append(rent_item)

    def return_book(self, new_rent_item):
        """
        Returneaza o carte
        :param new_rent_item: RentItem ul de modificat
        :type new_rent_item: RentItem
        :return: rentitem ul modificat
        :rtype: RentItem
        :raises: RentItemNotFoundException daca nu exista rentitem-ul, sau RentItemAlreadyReturned daca a fost deja returnat
        """
        found = self.finditem(new_rent_item)
        if found is None:
            raise RentItemNotFoundException()
        self.__items.remove(new_rent_item)
        return new_rent_item


    def get_all(self):
        """
        Returneaza toti itemii din lista
        :return: lista de itemi
        :rtype: list of RentItem objects
        """
        return self.__items

    def size(self):
        return len(self.__items)

   # def getAllForDisc(self, disc):
      #  """
     #   Return all the grades for all the students from all disciplines
    #    disc - string, the discipline
    #    return list of StudentGrade's
   #     """
     #   try:
    #        f = open(self.__fname, "r")
      #  except IOError:
        # the file is not created yet
      #  return None
     #   try:
    #        rez = []  # StudentGrade instances
     #   line = f.readline().strip()
       # while line != "":
      #      attrs = line.split(",")
      #  # if this line refers to the requested student
     #   if attrs[1] == disc:
  #          gr = StudentGrade(attrs[0], attrs[1], float(attrs[2]))
    #    rez.append(gr)
    #    line = f.readline().strip()
    #    f.close()
     #   return rez
      #  except IOError:
       # raise RepositorException("Unable to read grades from the file")

def test_store_sale_item():
    test_repo = RentItemRepoMemory()
    c1 = Client('455', 'dsdf', '3699885522023', 0)
    c2 = Client('845', 'ertr', '8558962002630', 1)
    c3 = Client('34', 'fvf', '8558333333630', 0)
    b1 = Book('965', 'asd', 'wsc', 'wsd', 0)
    b2 = Book('542', 'wdsc', 'sc', 'spoj', 2)
    b3 = Book('222', 'wedfv', 'e', 'fv', 1)
    r1 = RentItem(b1, c1)
    r2 = RentItem(b2, c2)
    r3 = RentItem(b2, c1)
    r4 = RentItem(b2, c2)
    test_repo.store(r1)

    assert (test_repo.size() == 1)
    test_repo.store(r2)
    assert (test_repo.size() == 2)
    test_repo.store(r3)
    try:
        test_repo.store(r4)
        assert False
    except RentAlreadyAssignedException:
        assert True

    test_repo.return_book(r1)
    assert (test_repo.size() == 2)

    try:
        test_repo.return_book(r1)
        assert False
    except RentItemNotFoundException:
        assert True


test_store_sale_item()
