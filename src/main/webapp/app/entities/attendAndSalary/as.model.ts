export interface Ias {
  id: number;
  name?: string | null;
  baseSalary?: number | null;
  attendanceDays?: number | null;
  totalSalary?: number | null;
}
export type Newas = Omit<Ias, 'id'> & { id: null };
