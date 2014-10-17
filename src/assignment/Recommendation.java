package assignment;

import java.text.DecimalFormat;
import java.util.Random;

public class Recommendation {

	/* Etudiant 1 */
	public static String NAME1 = "Dominique Roduit";
	public static int SCIPER1 = 234868;
	
	/* Etudiant 2 - laissez tel quel si vous avez codé le projet tout seul */
	public static String NAME2 = "Thierry Treyer";
	public static int SCIPER2 = 235116;
	
	static Random random = new Random();
	
	public static void main(String[] args) {
		double[][] testMatrix = Recommendation.createMatrix(20, 20, 5, 6);

		
		System.out.println(matrixToString(testMatrix));
	}
	
	/** 
	 * Convertion d'une matrice en chaine de caractère
	 * @param A Matrice à convertir
	 * @author Dominique
	 * @return La matrice passée en argument sous forme de chaine de caractère
	 */
	public static String matrixToString(double[][] A) {
		if(!isMatrix(A)) return null;
		
		// Formattage des valeurs de la matrice à 1 décimals au min et au max
		DecimalFormat f = new DecimalFormat();
		f.setMinimumFractionDigits(1);
		f.setMaximumFractionDigits(1);
		
		// Chaine contenant la matrice
		String SMatrix = "{\n";
		
		// On parcours les lignes de la matrice
		for(int i=0; i<A.length; ++i) {
			 // Une tabulation et debut de la ligne
			SMatrix += "\t{";
			
			// On parcours les colonnes de la matrice
			for(int j=0; j<A[i].length; ++j) {
				SMatrix += f.format(A[i][j]);
				// Ajout d'une virgule après chaque valeur sauf la dernière
				if(j!=A[i].length-1) SMatrix += ",";
			}
			
			// fin de la ligne de matrice
			SMatrix += "}";
			// Ajout d'une virgule après chaque ligne de matrice excepté la dernière
			if(i!=A.length-1) SMatrix += ","; 
			// Retour à la ligne après chaque ligne de matrice
			SMatrix += "\n"; 
		}
		SMatrix += "};";
		
		return SMatrix;
	}
	
	public static boolean isMatrix( double[][] A ) {
		/* A n'est pas null ou vide */
		if (A == null || A.length == 0) {
			return false;
		}

		/* La première ligne de A n'est pas null ou vide */
		if (A[0] == null || A[0].length == 0) {
			return false;
		}

		/* Les lignes de A ne sont pas null et ont la même taille */
		int length = A[0].length;
		for (int i = 1, l = A.length; i < l; i++) {
			if (A[i] == null) {
				return false;
			}

			if (A[i].length != length) {
				return false;
			}
		}

		return true;
	}
	
	public static double[][] multiplyMatrix(double[][] A, double[][] B) {
		/* Matrices A et B valides */
		if (isMatrix(A) == false || isMatrix(B) == false) {
			return null;
		}

		/* Matrices de tailles compatibles */
		if (A[0].length != B.length) {
			return null;
		}

		int n = A.length; /* Nombre de lignes de la matrice finale */
		int m = B[0].length; /* Nombre de colonnes de la matrice finale */
		int p = A[0].length; /* Longueur d'une multiplication */
		double[][] C = new double[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < p; k++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}

		return C;	
	}
	
	/**
	 * Créer une matrice a partir d'une taille donnée et d'une plage de valeur
	 * aléatoire
	 * @param n Lignes 
	 * @param m Colonnes
	 * @param k Borne inférieure du randomize
	 * @param l Borne supérieure du randomize
	 * @author Dominique
	 * @return Matrice nxm contenant des nombres réels générés aléatoirements
	 */
	public static double[][] createMatrix( int n, int m, int k, int l) {
		double randValue;
		
		// Si les dimensions de la matrices ou la plage ne valeurs ne sont pas correctes
		if(m<=0 || n<=0 || l<=k) return null;
		
		// Matrice
		double[][] matrice = new double[n][m];
		
		// On parcours chaque ligne de la matrice
		for(int ligne=0; ligne<n; ++ligne) {
			// On parcours chaque colonne
			for(int c=0; c<m; ++c) {
				// Génération d'une valeur réelle aléatoire comprise entre k et l
				randValue = k+(l-k)*random.nextDouble();
				
				//Remplissage de la matrice
				matrice[ligne][c] = randValue;
			}
		}
		
		// On ne retourne la matrice que si celle-ci est correcte
		if(isMatrix(matrice)) 
			return matrice;
		else
			return null;
	}
	
	public static double rmse(double[][] M, double[][] P) {
		/* TODO: Dominique */
		/* Méthode à coder */	
		return 0;
	}
	
	public static double updateUElem( double[][] M, double[][] U, double[][] V, int r, int s ) {
		/* Matrices valides */
		if (isMatrix(M) == false || isMatrix(U) == false || isMatrix(V) == false) {
			return -1;
		}

		int n = M.length;
		int m = M[0].length;
		int d = V.length;

		/* r et s ont des valeurs valides */
		if (r < 0 || r >= n || s < 0 || s >= d) {
			return -1;
		}

		/* U a une taille valide */
		if (U.length != n || U[0].length != d) {
			return -1;
		}

		/* V a une taille valide */
		if (V.length != d || V[0].length != m) {
			return -1;
		}

		double numerator = 0;
		double denominator = 0;

		for (int j = 0; j < m; j++) {
			if (M[r][j] == 0) continue;

			double suv = 0;
			for (int k = 0; k < d; k++) {
				if (k == s) continue;
				suv += U[r][k] * V[k][j];
			}

			double vsj = V[s][j];
			double mrj = M[r][j];

			numerator   += vsj * (mrj - suv);
			denominator += vsj * vsj;
		}

		return numerator / denominator;
	}
	
	public static double updateVElem( double[][] M, double[][] U, double[][] V, int r, int s ) {
		/* Matrices valides */
		if (isMatrix(M) == false || isMatrix(U) == false || isMatrix(V) == false) {
			return -1;
		}

		int n = M.length;
		int m = M[0].length;
		int d = V.length;

		/* r et s ont des valeurs valides */
		if (r < 0 || r >= n || s < 0 || s >= d) {
			return -1;
		}

		/* U a une taille valide */
		if (U.length != n || U[0].length != d) {
			return -1;
		}

		/* V a une taille valide */
		if (V.length != d || V[0].length != m) {
			return -1;
		}

		double numerator = 0;
		double denominator = 0;

		for (int i = 0; i < n; i++) {
			if (M[i][s] == 0) continue;

			double suv = 0;
			for (int k = 0; k < d; k++) {
				if (k == r) continue;
				suv += U[i][k] * V[k][s];
			}

			double uir = U[i][r];
			double mis = M[i][s];

			numerator   += uir * (mis - suv);
			denominator += uir * uir;
		}

		return numerator / denominator;
	}
	
	public static double[][] optimizeU( double[][] M, double[][] U, double[][] V) {
		/* TODO: Thierry */
		/* Méthode à coder */	
		return null;		
	}

	public static double[][] optimizeV( double[][] M, double[][] U, double[][] V) {
		/* TODO: Thierry */
		/* Méthode à coder */	
		return null;		
	}
	
	public static int[] recommend( double[][] M, int d) {
		/* TODO: Dominique */
		/* Méthode à coder */	
		return null;
	}
}


