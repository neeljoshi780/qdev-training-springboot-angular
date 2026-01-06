/**
 * Customer model
 * Matches CustomerResponseDto from backend
 */
export interface Customer {
  id: number;
  firstName: string;
  lastName: string;
  address1: string;
  address2: string;
  dateOfBirth: string;
  mobile: string;
  age: number;
  gender: string;
  email: string;
}