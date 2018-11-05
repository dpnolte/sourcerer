/* generated @ 2018-11-05T13:36:06.248 */
import "./base.elements";
import "./generated-cardview.attributes";
, import "./generated.attributes";
declare module "LayoutTypes" {
  interface NormalizedCardViewElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<CardViewAttributes, LayoutAttributes> {
    type: "cardView";
  }
  interface DenormalizedCardViewElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<CardViewAttributes, LayoutAttributes, ChildrenViewAttributes, ViewGroupLayoutParamsAttributes> {
    type: "cardView";
  }}
