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
## @deftypefn {} {@var{retval} =} lab3_3 (@var{input1}, @var{input2})
##
## @seealso{}
## @end deftypefn

## Author: Paula <Paula@DESKTOP-QMOV01R>
## Created: 2022-11-01

function retval = lab3_3 (N=5000)
  bile = ['RRRRRAAAVV'];
  k1 = 0;
  k2 = 0;
  for i = 1:N
    b = randsample(bile,3,replacement=false);
    if b(1) == 'R' || b(2) == 'R' || b(3) == 'R'
      if b(1)=='R' && b(2)=='R'&& b(3)=='R'
        k1++;
     else k2++;
    endif
    endif
   endfor
   retval = k1/k2;
    ##retval = contor/N;
endfunction
