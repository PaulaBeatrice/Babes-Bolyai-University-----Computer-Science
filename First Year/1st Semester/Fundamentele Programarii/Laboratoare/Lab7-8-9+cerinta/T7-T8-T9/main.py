from domain.validators import BookValidator, ClientValidator, RentItemValidator
from repository.book_repo import InMemoryRepository
from repository.client_repo import InMemoryRepository_c, InMemoryRepoDict_c
from repository.repo_rent_item import RentItemRepoMemory
from repository.book_repo import BookRepoFile
from repository.client_repo import ClientRepoFile
from service.book_service import BookService
from service.client_service import ClientService
from service.rent_item_service import RentItemService
from ui.console import Console

val_book = BookValidator()
repo_book = InMemoryRepository()
#repo_book_file = BookRepoFile('data/books.txt')
srv_book = BookService(repo_book, val_book)

val_client = ClientValidator()
repo_client = InMemoryRepository_c()
#repo_dict_b = InMemoryDict()
#repo_dict_c = InMemoryDict_c()
#repo_client_file = ClientRepoFile('data/clients.txt')
srv_client = ClientService(repo_client, val_client)

#book_srv = BookService(repo, val_book)
#client_srv = ClientService(repo_c, val_c)
rent_item_repo = RentItemRepoMemory()
rent_item_val = RentItemValidator()
srv_rent_item = RentItemService(rent_item_repo, rent_item_val, repo_book, repo_client)

ui = Console(srv_book, srv_client, srv_rent_item)
ui.show_ui()
