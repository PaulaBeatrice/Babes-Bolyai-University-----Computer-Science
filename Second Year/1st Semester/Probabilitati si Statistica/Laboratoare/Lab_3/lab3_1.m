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
## @deftypefn {} {@var{retval} =} lab3_1 (@var{input1}, @var{input2})
##
## @seealso{}
## @end deftypefn

## Author: Paula <Paula@DESKTOP-QMOV01R>
## Created: 2022-11-01

function retval = lab3_1 (N=5000)
  bile = ["R" "R" "R" "R" "R" "A" "A" "A" "V" "V"];
  contor = 0;
  for i = 1:N
    b = randsample(bile,3,replacement=false);
    for j=1:length(b)
      if b(j) == "R"
        contor++;
        break;
      endif
    endfor
   endfor
    retval = contor/N;
end
