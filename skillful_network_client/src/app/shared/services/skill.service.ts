import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Skill } from '../models/skill';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class SkillService {
  private skillUrl: string;
  private userUrl: string;

  constructor(private http: HttpClient) {
    this.skillUrl = 'http://localhost:8080/skills';
    this.userUrl = 'http://localhost:8080/users';
  }
  public findAll(): Observable<Skill[]>{
    return this.http.get<Skill[]>(this.skillUrl);
  }
  public getSkillsByUser(id :number) : Observable<Skill[]>{
    return this.http.get<Skill[]>(`${this.userUrl}/${id}/skills`);
  }
  public getAllUserBySkill(id : number) : Observable<User[]>{
    return this.http.get<User[]>(`${this.skillUrl}/${id}/users`);
  }
}
