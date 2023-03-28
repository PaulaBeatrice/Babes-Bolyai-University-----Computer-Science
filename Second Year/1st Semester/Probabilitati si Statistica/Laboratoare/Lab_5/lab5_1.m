## Copyright (C) 2022 Paula
##
## This program is free software: you can redistribute it and/or modify
## it under the terms of the GNU General Public License as published by
## the Free Software Foundation, either version 3 of the License, or
## (at your option) any later version.
##
## This program is distributed in the hope that it will be useful,
## but WITHOUT ANY WARRANTY; without even the implied warranty of
## MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
## GNU General Public License for more details.
##
## You should have received a copy of the GNU General Public License
## along with this program.  If not, see <https://www.gnu.org/licenses/>.

## -*- texinfo -*-
## @deftypefn {} {@var{retval} =} lab4_1 (@var{input1}, @var{input2})
##
## @seealso{}
## @end deftypefn

## Author: Paula <Paula@DESKTOP-QMOV01R>
## Created: 2022-12-05

# daca e intre 0 si p1 returneaza X1
# daca e intre p1 si p2 returnam X2 si tot asa...

function result = lab4_1 (X=[0,1,2,3], p=[0.46,0.4,0.1,0.04],N=100)
  for j=1:N
    rnd = rand;
    suma = 0;
    range = 0;
    while(rnd > suma)
      suma = suma + p(range+1);
      range++;
    endwhile
    res(j) = range - 1;
  endfor
  result = res;
  a=hist(result,X);
  b=a/N;
  bar(X,b,'facecolor','y');
endfunction
