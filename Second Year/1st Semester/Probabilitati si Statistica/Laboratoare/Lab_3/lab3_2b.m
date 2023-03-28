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
## @deftypefn {} {@var{retval} =} lab3_2b (@var{input1}, @var{input2})
##
## @seealso{}
## @end deftypefn

## Author: Paula <Paula@DESKTOP-QMOV01R>
## Created: 2022-11-01

function retval = lab3_2b ()
  x = binornd(5, 1/3, 1, 5000);
  r1 = mean(x == 2)
  r2 = binopdf(2,5,1/3)
 # retval = r1/r2;
endfunction
