class Main inherits IO {
	grid : List;
	gameover : Bool;
	player : Int;
	winner : Int;
	count : Int;

	rem(dividend : Int, divisor : Int) : Int {
		dividend - dividend / divisor * divisor
	};

	print_grid() : Object { --prints the current status of the grid
		let i : Int <- 0 in
			while i < 3 loop {
				let j : Int <- 0 in
					while j < 3 loop {
						let t : Int <- i * 3 + j in
							if grid.at(t) = 0 then out_string("   ")
							else if grid.at(t) = 1 then out_string(" X ")
							else out_string(" O ")
							fi
							fi;
						if j = 2 then out_string("") else out_string("|") fi;
						j <- j + 1;
					}
					pool;
				if i = 2 then out_string("\n\n") else out_string("\n-----------\n") fi;
				i <- i + 1;
			}
			pool
	};

	is_valid(index : Int) : Bool {	--checks if given cell is empty
		if grid.at(index) = 0 then true else false fi
	};

	legal(x : Int, y : Int) : Bool {	--checks if given cell exists
		if x < 1 then false 
		else if 3 < x then false 
		else if y < 1 then false 
		else if 3 < y then false 
		else true
		fi
		fi
		fi
		fi
	};

	check_row(i : Int) : Bool {	--checks if ith row has all 3 X's or 3 O's
		if grid.at(i * 3) = grid.at(i * 3 + 1) then grid.at(i * 3 + 1) = grid.at(i * 3 + 2) else false fi
	};
	
	check_col(i : Int) : Bool {	--checks if ith row has all 3 X's or 3 O's
		if grid.at(i) = grid.at(i + 3) then grid.at(i + 3) = grid.at(i + 6) else false fi
	};

	check_dia1() : Bool {		--checks if first diagonal has all 3 X's or 3 O's
		if grid.at(0) = grid.at(4) then grid.at(4) = grid.at(8) else false fi
	};

	check_dia2() : Bool {		--checks if second diagonal has all 3 X's or 3 O's
		if grid.at(2) = grid.at(4) then grid.at(4) = grid.at(6) else false fi
	};

	is_match(i : Int, j : Int) : Bool {	--checks if there is a match after the last move
			if check_row(i) then true
			else if check_col(j) then true
			else if i + j = 2 then 
				if check_dia2() then true else if i = 1 then check_dia1() else false fi fi
			else if rem(i+j, 2) = 0 then check_dia1()
			else false
			fi
			fi
			fi
			fi
	};
	
	main() : Object {
		{
			grid <- new List.make(9,0);
			gameover <- false;
			player <- 1; --player 1 starts the game
			winner <- 0; 

			while not gameover loop { --loop till the game is not over
				print_grid();
				if player = 1 then out_string("Player 1's(X) move.\n") else out_string("Player 2's(O) move.\n") fi;
				let x : Int, y : Int in {
					out_string("Row: ");
					x <- in_int();
					out_string("Column: ");
					y <- in_int();
					if not legal(x, y) then out_string("Invalid position.\n")
					else {
						x <- x - 1;
						y <- y - 1;
						let index : Int <- x * 3 + y in
							if not is_valid(index) then out_string("Invalid move.\n")
							else {
								if player = 1 then grid.assign(index,1)
								else grid.assign(index,2) 
								fi;
								if is_match(x, y) then {gameover <- true; winner <- player;} else 0 fi;
								count <- count + 1;
								if count = 9 then gameover <- true else 0 fi;
								player <- 3 - player;
							}
							fi;
					}
					fi;
				};
				out_string("\n");
			}
			pool;

			print_grid();
			if winner = 1 then out_string("Player 1(X) won!\n")
			else if winner = 2 then out_string("Player 2(O) won!\n")
			else out_string("Match draw!\n")
			fi
			fi;
		}
	};
};
