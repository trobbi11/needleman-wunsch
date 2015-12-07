
public class NW {

	public static void main(String[] args) {
		class Blosum{
			  private String residues = "ARNDCQEGHILKMFPSTWYV";
			  
			  private int[][] residuescores = 
			            /* A  R  N  D  C  Q  E  G  H  I  L  K  M  F  P  S  T  W  Y  V */
			  { /* A */ {  5                                                          },
			    /* R */ { -2, 7                                                       },
			    /* N */ { -1,-1, 7                                                    },
			    /* D */ { -2,-2, 2, 8                                                 },
			    /* C */ { -1,-4,-2,-4,13                                              },
			    /* Q */ { -1, 1, 0, 0,-3, 7                                           },
			    /* E */ { -1, 0, 0, 2,-3, 2, 6                                        },
			    /* G */ {  0,-3, 0,-1,-3,-2,-3, 8                                     },
			    /* H */ { -2, 0, 1,-1,-3, 1, 0,-2,10                                  },
			    /* I */ { -1,-4,-3,-4,-2,-3,-4,-4,-4, 5                               },
			    /* L */ { -2,-3,-4,-4,-2,-2,-3,-4,-3, 2, 5                            },
			    /* K */ { -1, 3, 0,-1,-3, 2, 1,-2, 0,-3,-3, 6                         },
			    /* M */ { -1,-2,-2,-4,-2, 0,-2,-3,-1, 2, 3,-2, 7                      },
			    /* F */ { -3,-3,-4,-5,-2,-4,-3,-4,-1, 0, 1,-4, 0, 8                   },
			    /* P */ { -1,-3,-2,-1,-4,-1,-1,-2,-2,-3,-4,-1,-3,-4,10                },
			    /* S */ {  1,-1, 1, 0,-1, 0,-1, 0,-1,-3,-3, 0,-2,-3,-1, 5             },
			    /* T */ {  0,-1, 0,-1,-1,-1,-1,-2,-2,-1,-1,-1,-1,-2,-1, 2, 5          },
			    /* W */ { -3,-3,-4,-5,-5,-1,-3,-3,-3,-3,-2,-3,-1, 1,-4,-4,-3,15       },
			    /* Y */ { -2,-1,-2,-3,-3,-1,-2,-3, 2,-1,-1,-2, 0, 4,-3,-2,-2, 2, 8    },
			    /* V */ {  0,-3,-3,-4,-1,-3,-3,-4,-4, 4, 1,-3, 1,-1,-3,-2, 0,-3,-1, 5 } 
			            /* A  R  N  D  C  Q  E  G  H  I  L  K  M  F  P  S  T  W  Y  V */
			  };
			  public int[][] score;

			  void buildscore(String residues, int[][] residuescores) {
			    // Allow lowercase and uppercase residues (ASCII code <= 127):
			    score = new int[127][127];
			    for (int i=0; i<residues.length(); i++) {
			      char res1 = residues.charAt(i);
			      for (int j=0; j<=i; j++) {
			        char res2 = residues.charAt(j);
			        score[res1][res2] = score[res2][res1] 
				  = score[res1][res2+32] = score[res2+32][res1] 
				  = score[res1+32][res2] = score[res2][res1+32] 
				  = score[res1+32][res2+32] = score[res2+32][res1+32] 
				  = residuescores[i][j];
			        System.out.print(residuescores[i][j] + " ");
			      }
			      System.out.println("");
			    }
			  }
			  public String getResidues(){
				  return residues;
			  }

			  public Blosum() 
			  { buildscore(residues, residuescores); }
			}
		class Alignment{
			Blosum b;
			int penalty = -8;
			String seq1 = "ACED";
			String seq2 = "AQGDC";
			int len1, len2;
			
			public Alignment(Blosum b, int penalty, String seq1, String seq2){
				this.b = b;
				this.seq1 = seq1;
				this.seq2 = seq2;
				this.penalty = penalty;
				this.len1 = len1;
				this.len2 = len2;
			}
			
			
			
		}

		Blosum blos = new Blosum();
	}

}
