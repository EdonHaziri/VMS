import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpService } from './http.service';
import { Observable } from 'rxjs';
import { Consignment } from '../models/Consignment.model';
import { User } from '../models/User.model';
import { Vehicle } from '../models/Vehicle.model';

@Injectable({
  providedIn: 'root'
})
export class ConsignmentService {
  serverAPI_URL = `http://localhost:8080`;

  constructor(
    private http: HttpClient,
    private httpService: HttpService
  ) { }

  getAllConsignments(email: string): Observable<{ notReceived: Consignment[], received: Consignment[], assigned: Consignment[] }> {
    const url = `${this.serverAPI_URL}/vehicles/all`;
    const httpOptions = this.httpService.getHttpHeader();
    return this.http.post<{ notReceived: Consignment[], received: Consignment[], assigned: Consignment[] }>(url, email, httpOptions);
  }

  addConsignment(payload: {
    user: User,
    vehicle: Vehicle
  }): Observable<boolean> {
    const url = `${this.serverAPI_URL}/vehicles/add`;
    const httpOptions = this.httpService.getHttpHeader();
    const user_roles = [];
    [...payload.user.roles].forEach(role => user_roles.push({ role: role }));

    console.log()
    const body = {
      ...payload,
      user: {
        ...payload.user,
        roles: user_roles,
        vehicle: payload.vehicle
      }
    }
    console.log(body);
    return this.http.post<boolean>(url, body, httpOptions);
  }

  deliverConsignment(payload: {
    assigned_consignment: Consignment
    employee: User
  }): Observable<{ user: User, consignment: Consignment }> {
    const user_roles = [];
    [...payload.employee.roles].forEach(role => user_roles.push({ role: role }));

    const body = {
      user: {
        ...payload.employee,
        roles: user_roles,
        consignments: [...payload.employee.consignments]
      },
      consignment: payload.assigned_consignment
    }
    const url = `${this.serverAPI_URL}/vehicles/deliver`;
    const httpOptions = this.httpService.getHttpHeader();
    return this.http.post<{ user: User, consignment: Consignment }>(url, body, httpOptions);
  }

}
