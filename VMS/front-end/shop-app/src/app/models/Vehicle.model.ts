import { User } from './User.model';

export interface Vehicle {
  id: number;
  description: string;
  user: User;
}