import { Component, OnInit, Input, SimpleChanges, OnChanges } from '@angular/core';
import { Skill } from 'src/app/shared/models/skill';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from 'src/app/shared/services/user.service';
import { SkillService } from 'src/app/shared/services/skill.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-skill-conf',
  templateUrl: './skill-conf.component.html',
  styleUrls: ['./skill-conf.component.scss']
})

export class SkillConfComponent implements OnInit {
  @Input() skillInfoGroup : FormGroup; 
  @Input() userSkilList : Skill[]; 

  num :number;
  titleAlert: string = 'This field is required';
  post: any = '';
  skills:string[];
  public listSkill: Skill[];
  public skill: string;
  isLoading:boolean;

  constructor(private formBuilder: FormBuilder , private route: ActivatedRoute,private service : UserService, private skillService: SkillService) {}

  ngOnInit(): void {
    this.num =  this.route.snapshot.params.id;
    this.skillService.getSkillsByUser(this.num).subscribe(data => {
      console.log(data)
      this.listSkill = data;
    }, error => console.log(error));

    this.listSkill =  this.userSkilList;
    this.skillInfoGroup.value['skillSet'] = this.listSkill;
    this.skillInfoGroup.valueChanges.subscribe(data=>{
      this.isLoading=false;
      this.skills = []
      if (data.skillUnit.length >1){
        this.isLoading=true;
        this.service.findByContain("skills",data.skillUnit).then(
            datas=>{
              for(let id in datas){
              this.skills.push(datas[id].name)  
            }
            this.isLoading = false;
            }
        )
      }
    })
  }

  myControl = new FormControl()

  addSkill() {
    this.listSkill.push(new Skill(this.skillInfoGroup.value['skillUnit']));
    this.skillInfoGroup.value['skillSet'] = this.listSkill;
  }
}


